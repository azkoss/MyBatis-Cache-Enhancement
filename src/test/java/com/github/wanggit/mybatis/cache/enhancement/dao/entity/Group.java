package com.github.wanggit.mybatis.cache.enhancement.dao.entity;

import java.io.Serializable;

public class Group implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_group.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_group.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_group.project_id
     *
     * @mbggenerated
     */
    private Integer projectId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cc_group
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_group.id
     *
     * @return the value of cc_group.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_group.id
     *
     * @param id the value for cc_group.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_group.name
     *
     * @return the value of cc_group.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_group.name
     *
     * @param name the value for cc_group.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_group.project_id
     *
     * @return the value of cc_group.project_id
     *
     * @mbggenerated
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_group.project_id
     *
     * @param projectId the value for cc_group.project_id
     *
     * @mbggenerated
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}