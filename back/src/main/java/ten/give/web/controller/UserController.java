package ten.give.web.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ten.give.domain.entity.user.Follow;
import ten.give.domain.entity.user.User;
import ten.give.domain.exception.NoSuchTargetException;
import ten.give.domain.exception.form.ResultForm;
import ten.give.web.form.*;
import ten.give.web.service.FollowService;
import ten.give.web.service.LoginService;
import ten.give.web.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Api(tags = "UserController")
public class UserController {

    private final LoginService loginService;
    private final UserService userService;
    private final FollowService followService;

    @ApiOperation(
            value = "login",
            notes = "login 하기" +
                    "http://localhost:8080/users/login" +
                    "{\n" +
                    "\t\"loginEmail\" : \"testA@test.com\"\n" +
                    "\t\"loginPassword\" : \"testA\"\n" +
                    "}")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(
                            name = "form",
                            value = "로그인 시 사용자가 입력할 email, password",
                            required = true,
                            dataType = "LoginForm",
                            paramType = "body",
                            defaultValue = "None"
                    )
            }
    )
    @ApiResponses({
            @ApiResponse(code=200, message="성공")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new NoSuchTargetException("잘못된 입력 입니다.");
        }

        String token = loginService.login(form.getLoginEmail(), form.getLoginPassword());

        log.info("Token? [ {} ]", token);

        if (token == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return null;
        }
        //로그인 성공 처리 TODO
        return ResponseEntity.ok().body(token);
    }

    @ApiOperation(
            value = "Join",
            notes = "회원 가입하기" +
                    "[ EX ] URL : http://localhost:8080/users/join")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(
                            name = "form",
                            value = "회원 가입 시 사용자가 입력할 회원 가입 form",
                            required = true,
                            dataType = "JoinForm",
                            paramType = "body",
                            defaultValue = "None"
                    )
            }
    )
    @ApiResponses({
            @ApiResponse(code=200, message="성공"),
    })
    @PostMapping("/join")
    public ResultForm join(@Valid @RequestBody JoinForm form){
        return userService.joinUser(form);
    }

    @ApiOperation(
            value = "withdrawal",
            notes = "회원 탈퇴하기 <br>" +
                    "<br> 로그인이 선행 되어 있어야 한다. " +
                    "[ EX ] URL : http://localhost:8080/users/withdrawal")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(
                            name = "authentication",
                            value = "로그인 회원 정보",
                            required = true,
                            dataType = "Authentication",
                            paramType = "header",
                            defaultValue = "None"
                    )
            }
    )
    @ApiResponses({
            @ApiResponse(code=200, message="성공"),
    })
    @PostMapping("/withdrawal")
    public ResultForm withdrawal(Authentication authentication){
        return userService.withdrawal(Long.valueOf(authentication.getName()));
    }

    @ApiOperation(
            value = "User infomation",
            notes = "회원 정보 조회 하기 <br>" +
                    "<br> 로그인이 선행 되어 있어야 한다. " +
                    "[ EX ] URL : http://localhost:8080/users/info")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(
                            name = "authentication",
                            value = "로그인 회원 정보",
                            required = true,
                            dataType = "Authentication",
                            paramType = "header",
                            defaultValue = "None"
                    )
            }
    )
    @ApiResponses({
            @ApiResponse(code=200, message="성공"),
    })
    @GetMapping("/info")
    public UserInfoForm showUserInfo(Authentication authentication){

        Optional<User> userByEmail = userService.getuserByAccountId(Long.valueOf(authentication.getName()));
        Long totalDonationCount = userService.getTotalDonationCount();

        if (userByEmail.isEmpty()){
            throw new NoSuchTargetException("존재 하지 않는 User 입니다.");
        }

        Long followingCount = followService.getFollowingCount(Long.valueOf(authentication.getName()));
        Long followerCount = followService.getFollowerCount(Long.valueOf(authentication.getName()));

        UserInfoForm userInfoForm = userByEmail.get().userTransferToUserInfo(totalDonationCount,followingCount,followerCount);

        return userInfoForm;

    }

    @ApiOperation(
            value = "User infomation edit",
            notes = "회원 정보 조회 하기 <br>" +
                    "<br> 로그인이 선행 되어 있어야 한다. " +
                    "[ EX ] URL : http://localhost:8080/users/edit")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(
                            name = "authentication",
                            value = "로그인 회원 정보",
                            required = true,
                            dataType = "Authentication",
                            paramType = "header",
                            defaultValue = "None"
                    ),
                    @ApiImplicitParam(
                            name = "updateParam",
                            value = "로그인 회원 정보 수정 시 사용자가 입력하는 값",
                            required = true,
                            dataType = "UserInfoForm",
                            paramType = "body",
                            defaultValue = "None"
                    )
            }
    )
    @ApiResponses({
            @ApiResponse(code=200, message="성공"),
    })
    @PostMapping("/edit")
    public UserInfoForm editUserInfo(Authentication authentication, @RequestBody UserInfoForm updateParam){

        Long userId = Long.valueOf(authentication.getName());

        Optional<User> userByEmail = userService.getuserByAccountId(userId);

        if (userByEmail.isEmpty()){
            throw new NoSuchTargetException("존재 하지 않는 User 입니다.");
        }

        UserInfoForm userInfoForm = userService.editUserInfo(userId, updateParam);

        return userInfoForm;
    }


    @ApiOperation(
            value = "email 찾기",
            notes = "email 찾기 <br>" +
                    "[ EX ] URL : http://localhost:8080/users/findemail")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(
                            name = "name",
                            value = "사용자 이름",
                            required = true,
                            dataType = "String",
                            paramType = "body",
                            defaultValue = "None"
                    ),
                    @ApiImplicitParam(
                            name = "phone number",
                            value = "사용자 전화번호",
                            required = true,
                            dataType = "String",
                            paramType = "body",
                            defaultValue = "None"
                    )
            }
    )
    @PostMapping("/findemail")
    public ResultForm findEmail(@RequestBody FindEmailForm form){
        return userService.findEmail(form.getName(),form.getPhoneNumber());
    }

    @ApiOperation(
            value = "password 찾기",
            notes = "password 찾기 <br>" +
                    "[ EX ] URL : http://localhost:8080/users/findpassword")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(
                            name = "name",
                            value = "사용자 이름",
                            required = true,
                            dataType = "String",
                            paramType = "body",
                            defaultValue = "None"
                    ),
                    @ApiImplicitParam(
                            name = "phone number",
                            value = "사용자 전화번호",
                            required = true,
                            dataType = "String",
                            paramType = "body",
                            defaultValue = "None"
                    )
            }
    )
    @PostMapping("/findpassword")
    public ResultForm findPassword(@RequestBody FindPasswordForm form){
        return userService.findPassword(form.getName(),form.getEmail());
    }

    @ApiOperation(
            value = "password 수정",
            notes = "password 수정 <br>" +
                    "[ EX ] URL : http://localhost:8080/users/editpassword")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(
                            name = "password",
                            value = "수정 될 비밀번호",
                            required = true,
                            dataType = "String",
                            paramType = "body",
                            defaultValue = "None"
                    )
            }
    )
    @PostMapping("/editpassword")
    public ResultForm editPassword(@RequestBody String password, Authentication authentication){
        log.info("password : {} ",password);
        return userService.editPassword(Long.valueOf(authentication.getName()),password);
    }



    /*// coolSMS 구현 로직 연결
    @GetMapping("/sendemail")
    public void sendSMS(@RequestParam(value="to") String to, HttpServletRequest request, HttpServletResponse response) throws CoolsmsException, IOException {


        request.setAttribute("smsToken","testToken");
        String redirect_uri="/users/test";

        response.sendRedirect(redirect_uri);

    }*/

    /*@GetMapping("/test")
    public String test(String smsToken, HttpServletRequest request){
        log.info("in /test uri");
        log.info("{}" , request.getAttribute("smsToken"));
        return smsToken;
    }*/

}
