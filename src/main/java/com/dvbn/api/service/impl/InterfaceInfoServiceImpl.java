package com.dvbn.api.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dvbn.api.common.ErrorCode;
import com.dvbn.api.exception.BusinessException;
import com.dvbn.api.exception.ThrowUtils;
import com.dvbn.api.model.dto.interfaceInfo.InterfaceInfoQueryRequest;
import com.dvbn.api.model.entity.*;
import com.dvbn.api.mapper.InterfaceInfoMapper;
import com.dvbn.api.model.vo.InterfaceInfoVO;
import com.dvbn.api.model.vo.PostVO;
import com.dvbn.api.model.vo.UserVO;
import com.dvbn.api.service.InterfaceInfoService;
import com.dvbn.api.service.UserService;
import me.chanjar.weixin.common.util.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author dvbn
* @description 针对表【interface_info】的数据库操作Service实现
* @createDate 2023-11-09 14:48:12
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{


    @Resource
    private UserService userService;
    /**
     * 校验
     *
     * @param interfaceInfo
     * @param add
     */
    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {

        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();

        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(name), ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }
    }

    /**
     * 获取查询条件
     *
     * @param interfaceInfoQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<InterfaceInfo> getQueryWrapper(InterfaceInfoQueryRequest interfaceInfoQueryRequest) {
        return null;
    }

    /**
     * 从 ES 查询
     *
     * @param interfaceInfoQueryRequest
     * @return
     */
    @Override
    public Page<InterfaceInfo> searchFromEs(InterfaceInfoQueryRequest interfaceInfoQueryRequest) {
        return null;
    }

    /**
     * 获取接口信息
     *
     * @param interfaceInfo
     * @param request
     * @return
     */
    @Override
    public InterfaceInfoVO getInterfaceInfoVO(InterfaceInfo interfaceInfo, HttpServletRequest request) {

        InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
        BeanUtil.copyProperties(interfaceInfo, interfaceInfoVO);

        return interfaceInfoVO;
    }

    /**
     * 分页获取接口封装
     *
     * @param interfaceInfoPage
     * @param request
     * @return
     */
    @Override
    public Page<InterfaceInfoVO> getInterfaceInfoVOPage(Page<InterfaceInfo> interfaceInfoPage, HttpServletRequest request) {
        List<InterfaceInfo> interfaceInfoList = interfaceInfoPage.getRecords();
        Page<InterfaceInfoVO> interfaceInfoVOPage = new Page<>(interfaceInfoPage.getCurrent(), interfaceInfoPage.getSize(), interfaceInfoPage.getTotal());
        if (CollectionUtils.isEmpty(interfaceInfoList)) {
            return interfaceInfoVOPage;
        }
        List<InterfaceInfoVO> list = interfaceInfoList.stream().map(item -> {
            InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
            BeanUtil.copyProperties(item, interfaceInfoVO);
            return interfaceInfoVO;
        }).collect(Collectors.toList());

        interfaceInfoVOPage.setRecords(list);
        return interfaceInfoVOPage;
    }
}




