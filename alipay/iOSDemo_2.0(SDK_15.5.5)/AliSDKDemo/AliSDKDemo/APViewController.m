//
//  ViewController.m
//  AliSDKDemo
//
//  Created by antfin on 17-10-24.
//  Copyright (c) 2017年 AntFin. All rights reserved.
//

#import "APViewController.h"
#import <AlipaySDK/AlipaySDK.h>

#import "APAuthInfo.h"
#import "APOrderInfo.h"
#import "APRSASigner.h"
#import "APWebViewController.h"


#define AP_SUBVIEW_XGAP   (20.0f)
#define AP_SUBVIEW_YGAP   (30.0f)
#define AP_SUBVIEW_WIDTH  (([UIScreen mainScreen].bounds.size.width) - 2*(AP_SUBVIEW_XGAP))

#define AP_BUTTON_HEIGHT  (60.0f)
#define AP_INFO_HEIGHT    (200.0f)

@interface APViewController ()

@property(nonatomic, strong)APWebViewController *webvc;

@end

@implementation APViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self layoutVCSubView];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)toInterfaceOrientation
{
    return NO;
}

- (BOOL)shouldAutorotate
{
    return NO;
}

- (UIInterfaceOrientationMask)supportedInterfaceOrientations
{
    return UIInterfaceOrientationMaskPortrait;
}

- (void)layoutVCSubView
{
    // NOTE: 全局设置
    self.title = @"支付宝Demo";
    self.view.backgroundColor = [UIColor whiteColor];

    // NOTE: 支付按钮，模拟支付流程
    CGFloat originalPosY = [UIApplication sharedApplication].statusBarFrame.size.height + 80.0f;
    [self generateBtnWithTitle:@"支付宝支付Demo" selector:@selector(doAPPay) posy:originalPosY];
    
    // NOTE: 授权按钮，模拟授权流程
    originalPosY += (AP_BUTTON_HEIGHT + AP_SUBVIEW_YGAP);
    [self generateBtnWithTitle:@"支付宝授权Demo" selector:@selector(doAPAuth) posy:originalPosY];
    
    // NOTE: 重要说明
    NSString* text = @"重要说明:\n本Demo为了方便向商户展示支付宝的支付流程，所以订单信息的加签过程放在客户端完成；\n在商户的真实App内，为了防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；\n商户privatekey等数据严禁放在客户端，订单信息的加签过程也务必放在服务端完成；\n若商户接入时不遵照此说明，因此造成了损失，需自行承担。";
    CGFloat infoHeight = [text boundingRectWithSize:CGSizeMake(AP_SUBVIEW_WIDTH, CGFLOAT_MAX)
                                            options:NSStringDrawingUsesLineFragmentOrigin | NSStringDrawingUsesFontLeading
                                         attributes:@{NSFontAttributeName:[UIFont boldSystemFontOfSize:14.0f]}
                                            context:nil].size.height;
    originalPosY = ([UIScreen mainScreen].bounds.size.height) - (infoHeight + 2) - AP_SUBVIEW_YGAP;
    UILabel* information = [[UILabel alloc]initWithFrame:CGRectMake(AP_SUBVIEW_XGAP, originalPosY, AP_SUBVIEW_WIDTH, infoHeight + 2)];
    information.numberOfLines = 0;
    information.backgroundColor = [UIColor clearColor];
    [information setFont:[UIFont boldSystemFontOfSize:14.0f]];
    [information setTextColor:[UIColor redColor]];
    information.text = text;
    [self.view addSubview:information];
}

- (void)generateBtnWithTitle:(NSString*)title selector:(SEL)selector posy:(CGFloat)posy
{
    UIButton* tmpBtn = [[UIButton alloc]initWithFrame:CGRectMake(AP_SUBVIEW_XGAP, posy, AP_SUBVIEW_WIDTH, AP_BUTTON_HEIGHT)];
    tmpBtn.backgroundColor = [UIColor colorWithRed:81.0f/255.0f green:141.0f/255.0f blue:229.0f/255.0f alpha:1.0f];
    tmpBtn.layer.masksToBounds = YES;
    tmpBtn.layer.cornerRadius = 4.0f;
    [tmpBtn setTitle:title forState:UIControlStateNormal];
    [tmpBtn addTarget:self action:selector forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:tmpBtn];
}


#pragma mark -
#pragma mark   ==============点击订单模拟支付行为==============
//
// 选中商品调用支付宝极简支付
//
- (void)doAPPay
{
    // 重要说明
    // 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
    // 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
    // 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
    /*============================================================================*/
    /*=======================需要填写商户app申请的===================================*/
    /*============================================================================*/
    NSString *appID = @"2018070560549460";
    
    // 如下私钥，rsa2PrivateKey 或者 rsaPrivateKey 只需要填入一个
    // 如果商户两个都设置了，优先使用 rsa2PrivateKey
    // rsa2PrivateKey 可以保证商户交易在更加安全的环境下进行，建议使用 rsa2PrivateKey
    // 获取 rsa2PrivateKey，建议使用支付宝提供的公私钥生成工具生成，
    // 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
    NSString *rsa2PrivateKey = @"MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCfHHIiGzZEOoo3pnWjYLErYM4jbK9aUkFAswymwtqJQBeMzyfRPBsMQ4YOKRaUjJra4tkBd50OdDjrt8bED71Tb5aY3TJj3wNSlKVTxrLVPxjdODf+iwZPBUiN6c9UwFfTGOa41XrNM2Lg0I2LCjWa6v+kmXngU5FY7N2oBhqYrI7fTUdjZnf8zGESaxmsaAvpuQglGvfJgRaV/fGMy1tqgQCk4MFDA8ukiFpaD8+aTvlKANn0FHsf23vavRvVleaBkN63PiPE8hi+85Y3/jM72vvuUpVMkqhx26rM+jKhAHl8gSEB4OaG0vV81jQqcTb1O/nRhFytyDnzaDCcIcCXAgMBAAECggEAKo83UDwrJdDqy7vw14Szn0DDouIdfXFnFuFNzwVr4y6LnhSDOAp1ya/+GaeP7Kg3e7kWBrhyAD3CQ/riZes33yuIAteX9PLgNqSgHGqXx0abZgD1qVaSl2x4Xc1esfLJCyQsAuDHej/2gOAIHFrcv9WAEA0ldoWj6T9iguEvGAsT0Tt0f/1D6VbmyIvjPTj/nJstgYn3f9fW2HIz7WkeZI+69Eaad0bnHl2A19yntkvbfl05y5YDeMVB/JR2yAqPAUXER25Nfm9iD/TLqE2lFc2CVQgONiqUU6vyD78/kb1OCwliwpPLk9TQ8ChVMoHKpySimfMu+/aOIhjkt4hRIQKBgQDa3GrCbIKN97BJquScKH3HYGLk7EUBXHyAKryJxXA15gyY58WB0kTsR4RJXnL/Nnd+l0GLG4n26PDv8aqPQbnaFsZjJgSBtYNx3V4CEgaxZOo8IBjmOCPoZH6oo3d62GZjLwQvczEQ6EosrcyeJt6pBYSsoB5ELsS8mY/Kld4tWQKBgQC6HGwlpAEI+Hr7wNwoY8EwvWayRI43AWZGciIPjMBaGEUYyrkwV5QMlipAZSybTo5ZDGcKuR33sBXv6vbewkd8bcJAugxRwl8E0h8IZ9bGZYzFfjizsPiSOP6k8AqKcJaV38ttC6BpLcoNDSvp6eKQnBtH9NOUUUC8SF1O22bvbwKBgQDMUvtYh/ValOL/44uKPkMu381z6yCBJNcW0M8z0B/yMdOQ7NB3Ytb1v87AsGyr1y24AX4vYIZs/Qvy9qtFExdvg9OyvMt4TP1qUEBzrmCRPKVxfIXCaKcdTrTwu6vQBDeSYVKG+gPUxQh6rcdZ3vAA3CYBA3s+LGt/p92WRFQoAQKBgF3QrgNbZNosx3HYiiIiBuSW1QnP3RCFOBUSu3CzNtMzoMEaj7d6rjqaB7pmRdIUC1WAFwEkx6JvtN83xjIMJRToDx+pqUQdguBfOx11mqJv1kJHmjWaY/LxoAtspSOcFYx/P4A8eCQM3OkvVo+XQoo65tWi6HS/cK54m/CAUKcpAoGAT3jzPIat3Xjiga4fUno/uLCgNLgI1ThDhj0ZovECxP3neW3aHhDWAn1+Udz1AXRamEVQgsc6nIDcOEJvC8iFIk+q7hnQOgaoTvBLB3/5m7OSDJKbr6Uf/hxm3R8hAQ8Zbq/9gEgjny9JVPNiOsH69SbXqmvQ7vJ07JptZtz5gac=";
    NSString *rsaPrivateKey = @"";
    /*============================================================================*/
    /*============================================================================*/
    /*============================================================================*/
    
    //partner和seller获取失败,提示
    if ([appID length] == 0 ||
        ([rsa2PrivateKey length] == 0 && [rsaPrivateKey length] == 0))
    {
        UIAlertController *alert = [UIAlertController alertControllerWithTitle:@"提示"
                                                                       message:@"缺少appId或者私钥,请检查参数设置"
                                                                preferredStyle:UIAlertControllerStyleAlert];
        UIAlertAction *action = [UIAlertAction actionWithTitle:@"知道了"
                                                         style:UIAlertActionStyleDefault
                                                       handler:^(UIAlertAction *action){
                                                           
                                                       }];
        [alert addAction:action];
        [self presentViewController:alert animated:YES completion:^{ }];
        return;
    }
    
    /*
     *生成订单信息及签名
     */
    //将商品信息赋予AlixPayOrder的成员变量
    APOrderInfo* order = [APOrderInfo new];
    
    // NOTE: app_id设置
    order.app_id = appID;
    
    // NOTE: 支付接口名称
    order.method = @"alipay.trade.app.pay";
    
    // NOTE: 参数编码格式
    order.charset = @"utf-8";
    
    // NOTE: 当前时间点
    NSDateFormatter* formatter = [NSDateFormatter new];
    [formatter setDateFormat:@"yyyy-MM-dd HH:mm:ss"];
//    order.timestamp = [formatter stringFromDate:[NSDate date]];
    order.timestamp = @"2018-08-10 10:15:00";
    
    // NOTE: 支付版本
    order.version = @"1.0";
    
    // NOTE: sign_type 根据商户设置的私钥来决定
    order.sign_type = (rsa2PrivateKey.length > 1)?@"RSA2":@"RSA";
    
    // NOTE: 商品数据
    order.biz_content = [APBizContent new];
//    order.biz_content.body = @"我是测试数据";
    order.biz_content.subject = @"1";
//    order.biz_content.out_trade_no = [self generateTradeNO]; //订单ID（由商家自行制定）
    order.biz_content.out_trade_no = @"S6RE1P8R48R5V11";
//    order.biz_content.timeout_express = @"30m"; //超时时间设置
    order.biz_content.total_amount = [NSString stringWithFormat:@"%.2f", 0.01]; //商品价格
    
    //将商品信息拼接成字符串
    NSString *orderInfo = [order orderInfoEncoded:NO];
    NSString *orderInfoEncoded = [order orderInfoEncoded:YES];
    NSLog(@"orderSpec = %@",orderInfo);
    NSLog(@"orderInfoEncoded = %@",orderInfoEncoded);
    
    // NOTE: 获取私钥并将商户信息签名，外部商户的加签过程请务必放在服务端，防止公私钥数据泄露；
    //       需要遵循RSA签名规范，并将签名字符串base64编码和UrlEncode
    NSString *signedString = nil;
    APRSASigner* signer = [[APRSASigner alloc] initWithPrivateKey:((rsa2PrivateKey.length > 1)?rsa2PrivateKey:rsaPrivateKey)];
    if ((rsa2PrivateKey.length > 1)) {
        signedString = [signer signString:orderInfo withRSA2:YES];
    } else {
        signedString = [signer signString:orderInfo withRSA2:NO];
    }
    NSLog(@"signedString1 = %@",signedString);
    
    // modify by wangyu, 模拟从服务器端获取加密后的字符串
    signedString = @"XHIeugjqpKzCtlKhqKkAPts3%2FqKt%2F4JJBWNWIsGGDlQ9BL80uVrkDo0Cdbr4JxErIr0kSyJNgnF1IluVsnByA0BNhVy3GzcMw1qKEl5KfY4YIEIlb%2F%2Be9XN2QKO1WJUF2uFb14%2F7ltnnp5zIi91%2FQn3BJIUqK1n4887lYPwDq%2FSSU4wGJq9g99aFWWCd30bdO6%2F%2B7eUlvrpRwjHn9KeKnSqP45n0mrsMLMKTYoyTLyCIYYsSQQwTrm2CaE%2BCByvGFQCCWAIvCbM9dQIar34SzH3ZQPG2KJaZoXGcDxZGAxAPC%2BK2ZyC44Zn5WUDsRUZG6EuvFFFTIEBLeHYA84EH%2BQ%3D%3D";
    NSLog(@"signedString2 = %@",signedString);
    
//    signedString = [order encodeValue:@"nF8FiQ/Lseg5bx/Dz8jMaUCp+wyPmv9tSDSAltEfdepcam7iTWnhRTMTF/JhFVqvmZGsT7LpwfRxCvzTHkRl75gY4kKjoD9bw1ZL6lie9IdmBN3mszRKeJamAEO9xjjDXRiDMZLgK6AeE0X28217yC8mfmw5HcH2MBO80Nrn9PYjordt1pofchMjQnvqpUkpe1LyacdV/12B5wq57do0ZRBOcrG1hgZ1Zc2F2mswtOJUevGmlwWcd3RaQu6jvCzSS7wmnhVad2/9I6aGkd+CUGGhz7RIuNBoWVRLKf3K5rSgls7oAL4auCgiFlhq1iOltsbg3gtV7T1etP5SjnyI6Q=="];
//    NSLog(@"signedString2 = %@",signedString);
    
    // NOTE: 如果加签成功，则继续执行支付
    if (signedString != nil) {
        //应用注册scheme,在AliSDKDemo-Info.plist定义URL types
        NSString *appScheme = @"alisdkdemo";
        
        // NOTE: 将签名成功字符串格式化为订单字符串,请严格按照该格式
        NSString *orderString = [NSString stringWithFormat:@"%@&sign=%@",
                                 orderInfoEncoded, signedString];
        
        NSArray *windowarr = [[UIApplication sharedApplication] windows];
        UIWindow *win = [windowarr objectAtIndex:0];
        BOOL isH = win.hidden;
        
        // NOTE: 调用支付结果开始支付
        [[AlipaySDK defaultService] payOrder:orderString fromScheme:appScheme callback:^(NSDictionary *resultDic) {
            NSLog(@"reslut = %@",resultDic);
        }];
    }
}


#pragma mark -
#pragma mark   ==============点击模拟授权行为==============

- (void)doAPAuth
{
    // 重要说明
    // 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
    // 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
    // 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
    /*============================================================================*/
    /*=======================需要填写商户app申请的===================================*/
    /*============================================================================*/
    NSString *pid = @"";
    NSString *appID = @"";
    
    // 如下私钥，rsa2PrivateKey 或者 rsaPrivateKey 只需要填入一个
    // 如果商户两个都设置了，优先使用 rsa2PrivateKey
    // rsa2PrivateKey 可以保证商户交易在更加安全的环境下进行，建议使用 rsa2PrivateKey
    // 获取 rsa2PrivateKey，建议使用支付宝提供的公私钥生成工具生成，
    // 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
    NSString *rsa2PrivateKey = @"";
    NSString *rsaPrivateKey = @"";
    /*============================================================================*/
    /*============================================================================*/
    /*============================================================================*/
    
    //pid和appID获取失败,提示
    if ([pid length] == 0 ||
        [appID length] == 0 ||
        ([rsa2PrivateKey length] == 0 && [rsaPrivateKey length] == 0))
    {
        UIAlertController *alert = [UIAlertController alertControllerWithTitle:@"提示"
                                                                       message:@"缺少pid或者appID或者私钥,请检查参数设置"
                                                                preferredStyle:UIAlertControllerStyleAlert];
        UIAlertAction *action = [UIAlertAction actionWithTitle:@"知道了"
                                                         style:UIAlertActionStyleDefault
                                                       handler:^(UIAlertAction *action){
                                                           
                                                       }];
        [alert addAction:action];
        [self presentViewController:alert animated:YES completion:^{ }];
        return;
    }
    
    //生成 auth info 对象
    APAuthInfo *authInfo = [APAuthInfo new];
    authInfo.pid = pid;
    authInfo.appID = appID;
    
    //auth type
    NSString *authType = [[NSUserDefaults standardUserDefaults] objectForKey:@"authType"];
    if (authType) {
        authInfo.authType = authType;
    }
    
    //应用注册scheme,在AlixPayDemo-Info.plist定义URL types
    NSString *appScheme = @"alisdkdemo";
    
    // 将授权信息拼接成字符串
    NSString *authInfoStr = [authInfo description];
    NSLog(@"authInfoStr = %@",authInfoStr);
    
    // 获取私钥并将商户信息签名,外部商户可以根据情况存放私钥和签名,只需要遵循RSA签名规范,并将签名字符串base64编码和UrlEncode
    NSString *signedString = nil;
    APRSASigner* signer = [[APRSASigner alloc] initWithPrivateKey:((rsa2PrivateKey.length > 1)?rsa2PrivateKey:rsaPrivateKey)];
    if ((rsa2PrivateKey.length > 1)) {
        signedString = [signer signString:authInfoStr withRSA2:YES];
    } else {
        signedString = [signer signString:authInfoStr withRSA2:NO];
    }
    
    // 将签名成功字符串格式化为订单字符串,请严格按照该格式
    if (signedString.length > 0) {
        authInfoStr = [NSString stringWithFormat:@"%@&sign=%@&sign_type=%@", authInfoStr, signedString, ((rsa2PrivateKey.length > 1)?@"RSA2":@"RSA")];
        [[AlipaySDK defaultService] auth_V2WithInfo:authInfoStr
                                         fromScheme:appScheme
                                           callback:^(NSDictionary *resultDic) {
                                               NSLog(@"result = %@",resultDic);
                                               // 解析 auth code
                                               NSString *result = resultDic[@"result"];
                                               NSString *authCode = nil;
                                               if (result.length>0) {
                                                   NSArray *resultArr = [result componentsSeparatedByString:@"&"];
                                                   for (NSString *subResult in resultArr) {
                                                       if (subResult.length > 10 && [subResult hasPrefix:@"auth_code="]) {
                                                           authCode = [subResult substringFromIndex:10];
                                                           break;
                                                       }
                                                   }
                                               }
                                               NSLog(@"授权结果 authCode = %@", authCode?:@"");
                                           }];
    }
}


#pragma mark -
#pragma mark   ==============产生随机订单号==============

- (NSString *)generateTradeNO
{
    static int kNumber = 15;
    NSString *sourceStr = @"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    NSMutableString *resultStr = [[NSMutableString alloc] init];
    srand((unsigned)time(0));
    for (int i = 0; i < kNumber; i++)
    {
        unsigned index = rand() % [sourceStr length];
        NSString *oneStr = [sourceStr substringWithRange:NSMakeRange(index, 1)];
        [resultStr appendString:oneStr];
    }
    return resultStr;
}

@end
