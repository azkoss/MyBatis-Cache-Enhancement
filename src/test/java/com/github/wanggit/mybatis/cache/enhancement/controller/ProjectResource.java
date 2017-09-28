package com.github.wanggit.mybatis.cache.enhancement.controller;

import com.github.pagehelper.PageHelper;
import com.github.wanggit.mybatis.cache.enhancement.dao.entity.Project;
import com.github.wanggit.mybatis.cache.enhancement.dao.entity.ProjectExample;
import com.github.wanggit.mybatis.cache.enhancement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String list(@RequestParam(value = "name", required = false) String name) {
        PageHelper.startPage(1, 4, true);
        if (null == name){
            List<Project> projects = rojectService.selectByExample(new ProjectExample());
            return String.valueOf(projects.size());
        }else {
            ProjectExample example = new ProjectExample();
            example.createCriteria().andNameEqualTo(name);
            List<Project> projects = rojectService.selectByExample(example);
            return String.valueOf(projects.size());
        }
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete() {
        rojectService.deleteByExample(new ProjectExample());
        return "ok";
    }

}
