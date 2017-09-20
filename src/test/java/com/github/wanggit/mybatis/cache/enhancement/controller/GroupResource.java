package com.github.wanggit.mybatis.cache.enhancement.controller;

import java.util.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.github.wanggit.mybatis.cache.enhancement.service.GroupService;


@Controller
@RequestMapping("/group")
public class GroupResource {

	@Autowired
	private GroupService roupService;

	@PostMapping("/save")
	@ResponseBody
	public String save(){
		 return null;
	}

	@PostMapping("/update")
	@ResponseBody
	public String update(){
		return null;
	}

	@GetMapping("/list")
	@ResponseBody
	public String list(){
		return null;
	}

	@PostMapping("/delete")
	@ResponseBody
	public String delete(){
		return null;
	}

}
