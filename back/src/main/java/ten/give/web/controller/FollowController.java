package ten.give.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ten.give.domain.entity.user.Follow;
import ten.give.domain.exception.form.ResultForm;
import ten.give.web.service.FollowService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@Api(tags = "EmailController")
@RequestMapping("/")
public class FollowController {

    private final FollowService followService;

    @ApiOperation(
            value = "Follow",
            notes = "Follow 하기 <br>" +
                    "[ EX ] URL : http://localhost:8080/follow/{toId}")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(
                            name = "authentication",
                            value = "Follow 할 ID",
                            required = true,
                            dataType = "Authentication",
                            paramType = "body",
                            defaultValue = "None"
                    ),
                    @ApiImplicitParam(
                            name = "toId",
                            value = "Follow 할 ID",
                            required = true,
                            dataType = "String",
                            paramType = "body",
                            defaultValue = "None"
                    )
            }
    )
    @PostMapping("/follow/{toId}")
    public ResultForm follow(@PathVariable Long toId, Authentication authentication){
        return followService.follow(Long.valueOf(authentication.getName()),toId);
    }

    @ApiOperation(
            value = "unFollow",
            notes = "unFollow 하기 <br>" +
                    "[ EX ] URL : http://localhost:8080/unfollow/{toId}")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(
                            name = "authentication",
                            value = "Follow 할 ID",
                            required = true,
                            dataType = "Authentication",
                            paramType = "body",
                            defaultValue = "None"
                    ),
                    @ApiImplicitParam(
                            name = "toId",
                            value = "Follow 할 ID",
                            required = true,
                            dataType = "String",
                            paramType = "body",
                            defaultValue = "None"
                    )
            }
    )
    @PostMapping("/unfollow/{followId}")
    public ResultForm unfollow(@PathVariable Long followId, Authentication authentication){
        return followService.unfollow(Long.valueOf(authentication.getName()),followId);
    }

    @ApiOperation(
            value = "show Following",
            notes = "Following 인원 보기 <br>" +
                    "[ EX ] URL : http://localhost:8080/followings")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(
                            name = "authentication",
                            value = "로그인 ID",
                            required = true,
                            dataType = "Authentication",
                            paramType = "body",
                            defaultValue = "None"
                    )
            }
    )
    @PostMapping("/followings")
    public List<Follow> followings(Authentication authentication){
        return followService.getFollowing(Long.valueOf(authentication.getName()));
    }

    @ApiOperation(
            value = "show Followers",
            notes = "Followers 인원 보기 <br>" +
                    "[ EX ] URL : http://localhost:8080/Followers")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(
                            name = "authentication",
                            value = "로그인 ID",
                            required = true,
                            dataType = "Authentication",
                            paramType = "body",
                            defaultValue = "None"
                    )
            }
    )
    @PostMapping("/followers")
    public List<Follow> Followers(Authentication authentication){
        return followService.getFollower(Long.valueOf(authentication.getName()));
    }


}
