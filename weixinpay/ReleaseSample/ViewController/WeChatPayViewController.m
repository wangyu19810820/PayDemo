//
//  SendMsgToWeChatViewController.m
//  ApiClient
//
//  Created by Tencent on 12-2-27.
//  Copyright (c) 2012年 Tencent. All rights reserved.
//

#import "WeChatPayViewController.h"
#import "WXApiRequestHandler.h"
#import "WXApiManager.h"
#import "RespForWeChatViewController.h"
#import "WechatAuthSDK.h"
#import "UIAlertView+WX.h"

@interface WeChatPayViewController ()<WXApiManagerDelegate,UITextViewDelegate, WechatAuthAPIDelegate>

@property (nonatomic) enum WXScene currentScene;
@property (nonatomic, strong) UIScrollView *footView;

@end

@implementation WeChatPayViewController

@synthesize currentScene = _currentScene;
//@synthesize appId = _appId;

#pragma mark - View Lifecycle
- (void)viewDidLoad {
    [super viewDidLoad];
    [self setupHeadView];
    [self setupLineView];
    [WXApiManager sharedManager].delegate = self;
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    // Return YES for supported orientations
    if ([[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPhone) {
        return (interfaceOrientation != UIInterfaceOrientationPortraitUpsideDown);
    } else {
        return YES;
    }
}

#pragma mark - Action
- (void)bizPay {//微信支付按钮
    NSString *res = [WXApiRequestHandler jumpToBizPay];
    if( ![@"" isEqual:res] ){
        UIAlertView *alter = [[UIAlertView alloc] initWithTitle:@"支付失败" message:res delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil];
        
        [alter show];
    }
    
}

#define RGBCOLOR(r,g,b) [UIColor colorWithRed:(r)/255.0f green:(g)/255.0f blue:(b)/255.0f alpha:1]

#define TIPSLABEL_TAG 10086
#define SCREEN_WIDTH [[UIScreen mainScreen] bounds].size.width
#define SCREEN_HEIGHT [[UIScreen mainScreen] bounds].size.height
#define BUFFER_SIZE 1024 * 100

static const int kHeadViewHeight = 135;
static const int kSceneViewHeight = 100;

#pragma mark - Private Methods
- (void)setupHeadView {
    UIView *headView = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREEN_WIDTH, kHeadViewHeight)];
    [headView setBackgroundColor:RGBCOLOR(0xe1, 0xe0, 0xde)];
    UIImage *image = [UIImage imageNamed:@"micro_messenger.png"];
    NSInteger tlx = (headView.frame.size.width -  image.size.width) / 2;
    NSInteger tly = 20;
    
    UIImageView *imageView = [[UIImageView alloc]initWithFrame:CGRectMake(tlx, tly, image.size.width, image.size.height)];
    [imageView setImage:image];
    [headView addSubview:imageView];
    
    UILabel *title = [[UILabel alloc]initWithFrame:CGRectMake(0, tly + image.size.height, SCREEN_WIDTH, 40)];
    [title setText:[NSString stringWithFormat:@"微信支付OpenAPI(%@) Sample Demo",[WXApi getApiVersion]]];
    title.font = [UIFont systemFontOfSize:17];
    title.textColor = RGBCOLOR(0x11, 0x11, 0x11);
    title.textAlignment = NSTextAlignmentCenter;
    title.backgroundColor = [UIColor clearColor];
    [headView addSubview:title];
    
    [self.view addSubview:headView];
}



#define LEFTMARGIN			12
#define TOPMARGIN			15
#define BTNWIDTH			140
#define BTNHEIGHT			40
#define ADDBUTTON_AUTORELEASE(idx, title, sel) [self addBtn:idx tit:title selector:sel]

-(UIButton*) addBtn:(int)idx tit:(NSString*)title selector:(SEL)sel
{
    CGRect rect;
    if(idx % 2 == 1) {
        rect = CGRectMake(LEFTMARGIN, 25*(idx/2) + TOPMARGIN*(idx/2+1), BTNWIDTH, BTNHEIGHT - 4);
    } else {
        rect = CGRectMake(LEFTMARGIN*2 + BTNWIDTH, 25*(idx/2-1) + TOPMARGIN*(idx/2), BTNWIDTH, BTNHEIGHT - 4);
    }
    UIButton *button = [UIButton buttonWithType:UIButtonTypeRoundedRect];
    button.frame = rect;
    button.tag = idx;
    [button setTitle:title forState:UIControlStateNormal];
    button.titleLabel.font = [UIFont systemFontOfSize:14.0];
    [button addTarget:self action:sel forControlEvents:UIControlEventTouchUpInside];
    [self.footView addSubview:button];
    return button;
}

- (void)setupLineView {
    self.footView = [[UIScrollView alloc]initWithFrame:CGRectMake(0, kHeadViewHeight + 2 , SCREEN_WIDTH, SCREEN_HEIGHT - kHeadViewHeight - 2 )];
    [self.footView setBackgroundColor:RGBCOLOR(0xef, 0xef, 0xef)];
    self.footView.contentSize = CGSizeMake(SCREEN_WIDTH, SCREEN_HEIGHT);
    [self.view addSubview:self.footView];
    
    int index = 1;
    ADDBUTTON_AUTORELEASE(index++,@"发起微信支付",@selector(bizPay));

    self.footView.contentSize = CGSizeMake(SCREEN_WIDTH, 25*index + TOPMARGIN*index);
}

@end
