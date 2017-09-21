package com.github.wanggit.mybatis.cache.enhancement.controller;

import java.util.*;
import javax.servlet.http.*;

import com.github.wanggit.mybatis.cache.enhancement.dao.entity.Project;
import com.github.wanggit.mybatis.cache.enhancement.dao.entity.ProjectExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.github.wanggit.mybatis.cache.enhancement.service.ProjectService;


@Controller
@RequestMapping("/project")
public class ProjectResource {

    @Autowired
    private ProjectService rojectService;

    @PostMapping("/save")
    @ResponseBody
    public String save(Project project) {
        project.setCreateTime(new Date());
        rojectService.insertSelective(project);
        return "ok";
    }

    @PostMapping("/update")
    @ResponseBody
    public String update() {
        return null;
    }

    @GetMapping("/list")
    @ResponseBody
    public String list() {
        return null;
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete() {
        rojectService.deleteByExample(new ProjectExample());
        return "ok";
    }

}
