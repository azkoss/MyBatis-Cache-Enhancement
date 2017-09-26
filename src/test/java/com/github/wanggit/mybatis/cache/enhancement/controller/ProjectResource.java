package com.github.wanggit.mybatis.cache.enhancement.controller;

import com.github.wanggit.mybatis.cache.enhancement.dao.entity.Project;
import com.github.wanggit.mybatis.cache.enhancement.dao.entity.ProjectExample;
import com.github.wanggit.mybatis.cache.enhancement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


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
        List<Project> projects = rojectService.selectByExample(new ProjectExample());
        return String.valueOf(projects.size());
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete() {
        rojectService.deleteByExample(new ProjectExample());
        return "ok";
    }

}
