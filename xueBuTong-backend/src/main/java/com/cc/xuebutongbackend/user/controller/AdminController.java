package com.cc.xuebutongbackend.user.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.xuebutongbackend.common.exception.ErrorCode;
import com.cc.xuebutongbackend.common.model.dto.BaseResponse;
import com.cc.xuebutongbackend.common.model.dto.PageRequest;
import com.cc.xuebutongbackend.common.utils.ResultUtils;
import com.cc.xuebutongbackend.common.utils.ThrowUtils;
import com.cc.xuebutongbackend.user.annotation.AuthCheck;
import com.cc.xuebutongbackend.user.constant.UserRole;
import com.cc.xuebutongbackend.user.model.dto.UserAddRequest;
import com.cc.xuebutongbackend.user.model.entity.User;
import com.cc.xuebutongbackend.user.model.service.impl.UserServiceImpl;
import com.cc.xuebutongbackend.user.model.vo.LoginUserVO;
import com.cc.xuebutongbackend.user.utils.UserUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/admin")
@RestController
public class AdminController {
    @Resource
    UserServiceImpl userService;

    @AuthCheck(mustRole = UserRole.ROLE_ADMIN)
    @PostMapping("/add")
    public BaseResponse<Long> addUser(@RequestBody UserAddRequest user) {
        ThrowUtils.throwIf(BeanUtil.isEmpty(user), ErrorCode.PARAMS_ERROR);
        String userAccount = user.getUserAccount();
        String userName = user.getUserName();
        Long l = userService.addUser(userAccount, userName);
        return ResultUtils.success(l);
    }

    @GetMapping("/get")
    public BaseResponse<LoginUserVO> getUser(@RequestParam Long id) {
        ThrowUtils.throwIf(id<0, ErrorCode.PARAMS_ERROR,"id不为负");
        User user = userService.getById(id);
        LoginUserVO userVO = BeanUtil.copyProperties(user, LoginUserVO.class);
        ThrowUtils.throwIf(userVO ==null,ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(userVO);
    }

    /*
    * TODO：删除*/


    /*
    * TODO：修改*/


    //=================================================================================================
    // 下面是批量操作的方法
    /*
     * TODO：批量增加，导入excel或者其他方式*/

    /*
     * TODO：批量删除，提交一个id的list也行*/

    /*
     * 批量修改，这个没想好，暂时搁置*/

    /*
     * 分页查询
     * TODO：这里只是做了个简单的全查，还需要加入条件查询*/
    @PostMapping("/get/pager")
    public BaseResponse<Page<LoginUserVO>> getUserPager(@RequestBody PageRequest pageRequest) {
        ThrowUtils.throwIf(pageRequest==null,ErrorCode.PARAMS_ERROR);
        int current = pageRequest.getCurrent();
        int pageSize = pageRequest.getPageSize();

//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq(ObjUtil.isNotNull(id), "id", id);
//        queryWrapper.eq(StrUtil.isNotBlank(userRole), "userRole", userRole);
//        queryWrapper.like(StrUtil.isNotBlank(userAccount), "userAccount", userAccount);
//        queryWrapper.like(StrUtil.isNotBlank(userName), "userName", userName);
//        queryWrapper.like(StrUtil.isNotBlank(userProfile), "userProfile", userProfile);
//        queryWrapper.orderBy(StrUtil.isNotEmpty(sortField), sortOrder.equals("ascend"), sortField);

        Page<User> userPage = userService.page(new Page<>(current, pageSize));
        List<LoginUserVO> userVOList = userPage.getRecords().stream()
                .map(UserUtil::getLoginUserVO)
                .collect(Collectors.toList());

        Page<LoginUserVO> userVOPage = new Page<>(current, pageSize, userPage.getTotal());
        userVOPage.setRecords(userVOList);

        return ResultUtils.success(userVOPage);
    }
}
