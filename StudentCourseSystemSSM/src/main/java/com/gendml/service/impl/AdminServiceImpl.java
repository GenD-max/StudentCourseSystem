package com.gendml.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gendml.entity.Admin;
import com.gendml.mapper.AdminMapper;
import com.gendml.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Зөндөө
 * @create 2022-05-29 20:06
 */
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> queryAllAdmin() {
        return adminMapper.selectList(new QueryWrapper<Admin>());
    }

    @Override
    public int updateAdminByAid(Admin admin) {
        UpdateWrapper<Admin> wrapper = new UpdateWrapper<>();
        wrapper.eq("Aid",admin.getAid());
        int res = adminMapper.update(admin,wrapper);
        if(res == 0){
            log.info("更新失败！");
        }
        log.info("更新了{}条管理员信息。",res);
        return res;
    }

    @Override
    public Admin queryByAid(String Aid) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("Aid",Aid);
        return  adminMapper.selectOne(wrapper);
    }

    @Override
    public int getAdminNum() {
        return adminMapper.selectCount(new QueryWrapper<>());
    }
}
