package com.gendml.service;

import com.gendml.entity.Admin;

import java.util.List;

/**
 * @author Зөндөө
 * @create 2022-05-29 20:03
 */
public interface AdminService {

    public List<Admin> queryAllAdmin();

    public int updateAdminByAid(Admin admin);

    public Admin queryByAid(String Aid);

    public int getAdminNum();
}
