;Copyright (C) 2004-2012 VBAP Pty. Ltd.

;Website: http://tp.vbap.com.au


;This program is distributed in the hope that it will be useful,
;but WITHOUT ANY WARRANTY; without even the implied warranty of
;MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;GNU General Public License for more details.

;You should have received a copy of the GNU General Public License
;along with this program; if not, write to the Free Software
;Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

!define PORTABLEAPPNAME "BitviseSSHClient Portable"
!define NAME "BitviseSSHClientPortable"
!define APPNAME "BitviseSSHClient"
!define VER "2.0.4.51"
!define WEBSITE "http://tp.vbap.com.au/"
!define DEFAULTEXE "bvssh.exe"
!define DEFAULTAPPDIR "BitviseSSHClient"
!define DEFAULTSETTINGSPATH "settings"
!define LAUNCHERLANGUAGE "English"

;=== Program Details
Name "${PORTABLEAPPNAME}"
OutFile "..\..\${NAME}.exe"
Caption "${PORTABLEAPPNAME} | PortableApps.com"
VIProductVersion "${VER}"
VIAddVersionKey ProductName "${PORTABLEAPPNAME}"
VIAddVersionKey Comments "Allows ${APPNAME} to be run from a removable drive.  For additional details, visit ${WEBSITE}"
VIAddVersionKey CompanyName "VBAP Pty. Ltd."
VIAddVersionKey LegalCopyright "Danny Mensingh"
VIAddVersionKey FileDescription "${PORTABLEAPPNAME}"
VIAddVersionKey FileVersion "${VER}"
VIAddVersionKey ProductVersion "${VER}"
VIAddVersionKey InternalName "${PORTABLEAPPNAME}"
VIAddVersionKey LegalTrademarks "BitviseSSHClient is a Trademark of Bitvise Ltd. PortableApps.com is a Trademark of Rare Ideas, LLC."
VIAddVersionKey OriginalFilename "${NAME}.exe"
;VIAddVersionKey PrivateBuild ""
;VIAddVersionKey SpecialBuild ""

;=== Runtime Switches
CRCCheck On
WindowIcon Off
SilentInstall Silent
AutoCloseWindow True
RequestExecutionLevel user

; Best Compression
SetCompress Auto
SetCompressor /SOLID lzma
SetCompressorDictSize 32
SetDatablockOptimize On

;=== Include
;(Standard NSIS)
!include Registry.nsh
!include TextFunc.nsh
!insertmacro GetParameters

;(Custom)
!include ReadINIStrWithDefault.nsh

;=== Program Icon
Icon "..\..\App\AppInfo\appicon.ico"

;=== Languages
LoadLanguageFile "${NSISDIR}\Contrib\Language files\${LAUNCHERLANGUAGE}.nlf"
!include PortableApps.comLauncherLANG_${LAUNCHERLANGUAGE}.nsh

Var PROGRAMDIRECTORY
Var SETTINGSDIRECTORY
Var ADDITIONALPARAMETERS
Var EXECSTRING
Var PROGRAMEXECUTABLE
Var INIPATH
Var DISABLESPLASHSCREEN
Var ISDEFAULTDIRECTORY
Var SECONDARYLAUNCH
Var FAILEDTORESTOREKEY
Var MISSINGFILEORPATH
Var APPLANGUAGE


Section "Main"
	;=== Check if already running
	System::Call 'kernel32::CreateMutexA(i 0, i 0, t "${NAME}2") i .r1 ?e'
	Pop $0
	StrCmp $0 0 CheckForINI
		StrCpy $SECONDARYLAUNCH "true"

	CheckForINI:
	;=== Find the INI file, if there is one
		IfFileExists "$EXEDIR\${NAME}.ini" "" NoINI
			StrCpy "$INIPATH" "$EXEDIR"
			Goto ReadINI

	ReadINI:
		;=== Read the parameters from the INI file
		${ReadINIStrWithDefault} $0 "$INIPATH\${NAME}.ini" "${NAME}" "${APPNAME}Directory" "App\${DEFAULTAPPDIR}"
		StrCpy "$PROGRAMDIRECTORY" "$EXEDIR\$0"
		${ReadINIStrWithDefault} $0 "$INIPATH\${NAME}.ini" "${NAME}" "SettingsDirectory" "Data\${DEFAULTSETTINGSPATH}"
		StrCpy "$SETTINGSDIRECTORY" "$EXEDIR\$0"

		;=== Check that the above required parameters are present
		IfErrors NoINI

		${ReadINIStrWithDefault} $ADDITIONALPARAMETERS "$INIPATH\${NAME}.ini" "${NAME}" "AdditionalParameters" ""
		${ReadINIStrWithDefault} $PROGRAMEXECUTABLE "$INIPATH\${NAME}.ini" "${NAME}" "${APPNAME}Executable" "${DEFAULTEXE}"
		${ReadINIStrWithDefault} $DISABLESPLASHSCREEN "$INIPATH\${NAME}.ini" "${NAME}" "DisableSplashScreen" "false"

	;CleanUpAnyErrors:
		;=== Any missing unrequired INI entries will be an empty string, ignore associated errors
		ClearErrors

		;=== Correct PROGRAMEXECUTABLE if blank
		StrCmp $PROGRAMEXECUTABLE "" "" CheckForProgramINI
			StrCpy "$PROGRAMEXECUTABLE" "${DEFAULTEXE}"
			Goto CheckForProgramINI
			
	CheckForProgramINI:
		IfFileExists "$PROGRAMDIRECTORY\$PROGRAMEXECUTABLE" FoundProgramEXE NoProgramEXE

	NoINI:
		;=== No INI file, so we'll use the defaults
		StrCpy "$ADDITIONALPARAMETERS" ""
		StrCpy "$PROGRAMEXECUTABLE" "${DEFAULTEXE}"
		StrCpy "$DISABLESPLASHSCREEN" "false"

		IfFileExists "$EXEDIR\App\${DEFAULTAPPDIR}\${DEFAULTEXE}" "" NoProgramEXE
			StrCpy "$PROGRAMDIRECTORY" "$EXEDIR\App\${DEFAULTAPPDIR}"
			StrCpy "$SETTINGSDIRECTORY" "$EXEDIR\Data\${DEFAULTSETTINGSPATH}"
			StrCpy "$ISDEFAULTDIRECTORY" "true"
			GoTo FoundProgramEXE

	NoProgramEXE:
		;=== Program executable not where expected
		StrCpy $MISSINGFILEORPATH $PROGRAMEXECUTABLE
		MessageBox MB_OK|MB_ICONEXCLAMATION `$(LauncherFileNotFound)`
		Abort
		
	FoundProgramEXE:
		;=== Check if already running
		StrCmp $SECONDARYLAUNCH "true" CheckForSettings
		FindProcDLL::FindProc "$PROGRAMEXECUTABLE"                 
		StrCmp $R0 "1" WarnAnotherInstance CheckForSettings

	WarnAnotherInstance:
		MessageBox MB_OK|MB_ICONINFORMATION `$(LauncherAlreadyRunning)`
		Abort
	
	CheckForSettings:
		IfFileExists "$SETTINGSDIRECTORY\*.*" SettingsFound
		;=== No settings found
		StrCmp $ISDEFAULTDIRECTORY "true" CopyDefaultSettings
		CreateDirectory $SETTINGSDIRECTORY
		Goto SettingsFound
	
	CopyDefaultSettings:
		CreateDirectory "$EXEDIR\Data"
		CreateDirectory "$EXEDIR\Data\settings"
		CopyFiles /SILENT $EXEDIR\App\DefaultData\settings\*.* $EXEDIR\Data\settings
		GoTo SettingsFound

	SettingsFound:
		StrCmp $DISABLESPLASHSCREEN "true" GetPassedParameters
			;=== Show the splash screen before processing the files
			InitPluginsDir
			File /oname=$PLUGINSDIR\splash.jpg "${NAME}.jpg"	
			newadvsplash::show /NOUNLOAD 1500 0 0 -1 /L $PLUGINSDIR\splash.jpg

	GetPassedParameters:
		;=== Get any passed parameters
		${GetParameters} $0
		StrCmp "'$0'" "''" "" LaunchProgramParameters

		;=== No parameters
		StrCpy $EXECSTRING `"$PROGRAMDIRECTORY\$PROGRAMEXECUTABLE"`
		Goto AdditionalParameters

	LaunchProgramParameters:
		StrCpy $EXECSTRING `"$PROGRAMDIRECTORY\$PROGRAMEXECUTABLE" $0`

	AdditionalParameters:
		StrCmp $ADDITIONALPARAMETERS "" RegistryBackup

		;=== Additional Parameters
		StrCpy $EXECSTRING `$EXECSTRING $ADDITIONALPARAMETERS`

	RegistryBackup:
		StrCmp $SECONDARYLAUNCH "true" LaunchAndExit
		;=== Backup the registry
		${registry::KeyExists} "HKEY_CURRENT_USER\Software\Bitvise-BackupByBitviseSSHClientPortable" $R0
		StrCmp $R0 "0" GetAppLanguage
		${registry::KeyExists} "HKEY_CURRENT_USER\Software\Bitvise" $R0
		StrCmp $R0 "-1" GetAppLanguage
		${registry::MoveKey} "HKEY_CURRENT_USER\Software\Bitvise" "HKEY_CURRENT_USER\Software\Bitvise-BackupByBitviseSSHClientPortable" $R0
		Sleep 100
	
	GetAppLanguage:
		ReadEnvStr $APPLANGUAGE "PortableApps.comLanguageCode"
		StrCmp $APPLANGUAGE "" UpdatePaths ;if not set, move on 
		StrCmp $APPLANGUAGE "en-us" "" GetCurrentLanguage
			StrCpy $APPLANGUAGE "-"
			Goto SetAppLanguage

	GetCurrentLanguage:
		ReadINIStr $0 "$SETTINGSDIRECTORY\BitviseSSHClient_portable.reg" "HKEY_CURRENT_USER\Software\Bitvise" "Lang"
		StrCmp `"$APPLANGUAGE"` $0 UpdatePaths ;if the same, move on
		IfFileExists "$PROGRAMDIRECTORY\Lang\$APPLANGUAGE.txt" SetAppLanguage UpdatePaths

	SetAppLanguage:
		WriteINIStr "$SETTINGSDIRECTORY\BitviseSSHClient_portable.reg" "HKEY_CURRENT_USER\Software\Bitvise" `"Lang"` `"$APPLANGUAGE"`

	UpdatePaths:
		ReadINIStr $0 "$SETTINGSDIRECTORY\BitviseSSHClient_portable.reg" "HKEY_CURRENT_USER\Software\Bitvise" `"Editor"`
		StrCmp $0 "" RestoreTheKey
			StrCpy $1 $0 "" 3 
			StrCpy $2 $EXEDIR 1 ;current drive letter
			StrCpy $0 `"$2:\$1"`
			WriteINIStr "$SETTINGSDIRECTORY\BitviseSSHClient_portable.reg" "HKEY_CURRENT_USER\Software\Bitvise" `"Editor"` `$0`
	
	RestoreTheKey:
		IfFileExists "$SETTINGSDIRECTORY\BitviseSSHClient_portable.reg" "" LaunchNow
	
		IfFileExists "$WINDIR\system32\reg.exe" "" RestoreTheKey9x
			nsExec::ExecToStack `"$WINDIR\system32\reg.exe" import "$SETTINGSDIRECTORY\BitviseSSHClient_portable.reg"`
			Pop $R0
			StrCmp $R0 '0' LaunchNow ;successfully restored key

	RestoreTheKey9x:
		${registry::RestoreKey} "$SETTINGSDIRECTORY\BitviseSSHClient_portable.reg" $R0
		StrCmp $R0 '0' LaunchNow ;successfully restored key
		StrCpy $FAILEDTORESTOREKEY "true"
	
	LaunchNow:
		Sleep 100
		ExecWait $EXECSTRING
		
	CheckRunning:
		Sleep 1000
		FindProcDLL::FindProc "${DEFAULTEXE}"                  
		StrCmp $R0 "1" CheckRunning
		
		StrCmp $FAILEDTORESTOREKEY "true" SetOriginalKeyBack
		${registry::SaveKey} "HKEY_CURRENT_USER\Software\Bitvise" "$SETTINGSDIRECTORY\BitviseSSHClient_portable.reg" "" $0
		Sleep 100
	
	SetOriginalKeyBack:
		${registry::DeleteKey} "HKEY_CURRENT_USER\Software\Bitvise" $R0
		Sleep 100
		${registry::KeyExists} "HKEY_CURRENT_USER\Software\Bitvise-BackupByBitviseSSHClientPortable" $R0
		StrCmp $R0 "-1" TheEnd
		${registry::MoveKey} "HKEY_CURRENT_USER\Software\Bitvise-BackupByBitviseSSHClientPortable" "HKEY_CURRENT_USER\Software\Bitvise" $R0
		Sleep 100
		Goto TheEnd
		
	LaunchAndExit:
		Exec $EXECSTRING
	
	TheEnd:
		${registry::Unload}
		newadvsplash::stop /WAIT
SectionEnd