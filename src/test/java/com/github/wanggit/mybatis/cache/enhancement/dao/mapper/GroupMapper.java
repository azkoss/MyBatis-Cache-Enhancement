package com.github.wanggit.mybatis.cache.enhancement.dao.mapper;

import com.github.wanggit.mybatis.cache.enhancement.dao.entity.Group;
import com.github.wanggit.mybatis.cache.enhancement.dao.entity.GroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_group
     *
     * @mbggenerated
     */
    int countByExample(GroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_group
     *
     * @mbggenerated
     */
    int deleteByExample(GroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_group
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_group
     *
     * @mbggenerated
     */
    int insert(Group record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_group
     *
     * @mbggenerated
     */
    int insertSelective(Group record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_group
     *
     * @mbggenerated
     */
    List<Group> selectByExample(GroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_group
     *
     * @mbggenerated
     */
    Group selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_group
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Group record, @Param("example") GroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_group
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Group record, @Param("example") GroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_group
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Group record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_group
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Group record);
}