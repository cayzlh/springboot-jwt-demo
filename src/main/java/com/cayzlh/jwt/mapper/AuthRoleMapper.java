package com.cayzlh.jwt.mapper;

import com.cayzlh.jwt.model.AuthRole;
import com.cayzlh.jwt.model.example.AuthRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Description :
 *
 * <p>AuthRoleMapper</p>
 *
 * @author Ant丶
 * @date 2018-04-27.
 */
@Repository
public interface AuthRoleMapper {
    /**
     * Description:  使用Example统计总数
     *
     * @param  example example
     * @return countByExample 的结果.
     * @mbg.generated
     */
    long countByExample(AuthRoleExample example);

    /**
     * Description:  根据Example删除
     *
     * @param  example example
     * @return deleteByExample 的结果.
     * @mbg.generated
     */
    int deleteByExample(AuthRoleExample example);

    /**
     * Description:  根据主键删除
     *
     * @param  id id
     * @return deleteByPrimaryKey 的结果.
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * Description:  插入一条记录
     *
     * @param  record record
     * @return insert 的结果.
     * @mbg.generated
     */
    int insert(AuthRole record);

    /**
     * Description:  插入一条记录, 实现选择入库
     *
     * @param  record record
     * @return insertSelective 的结果.
     * @mbg.generated
     */
    int insertSelective(AuthRole record);

    /**
     * Description:  根据Example查询返回数据
     *
     * @param  example example
     * @return selectByExample 的结果.
     * @mbg.generated
     */
    List<AuthRole> selectByExample(AuthRoleExample example);

    /**
     * Description:  查询结果选择性返回
     *
     * @param  example example
     * @param  selective selective
     * @return selectByExampleSelective 的结果.
     * @mbg.generated
     */
    List<AuthRole> selectByExampleSelective(@Param("example") AuthRoleExample example, @Param("selective") AuthRole.Column ... selective);

    /**
     * Description:  根据主键查询返回数据
     *
     * @param  id id
     * @return selectByPrimaryKey 的结果.
     * @mbg.generated
     */
    AuthRole selectByPrimaryKey(Long id);

    /**
     * Description:  通过主键查询的结果选择性返回
     *
     * @param  id id
     * @param  selective selective
     * @return selectByPrimaryKeySelective 的结果.
     * @mbg.generated
     */
    AuthRole selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") AuthRole.Column ... selective);

    /**
     * Description:  Selective选择插入更新增强功能
     *
     * @param  record record
     * @param  example example
     * @return updateByExampleSelective 的结果.
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") AuthRole record, @Param("example") AuthRoleExample example);

    /**
     * Description:  根据Example更新数据
     *
     * @param  record record
     * @param  example example
     * @return updateByExample 的结果.
     * @mbg.generated
     */
    int updateByExample(@Param("record") AuthRole record, @Param("example") AuthRoleExample example);

    /**
     * Description:  根据主键更新数据, 可选择
     *
     * @param  record record
     * @return updateByPrimaryKeySelective 的结果.
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AuthRole record);

    /**
     * Description:  根据主键更新数据
     *
     * @param  record record
     * @return updateByPrimaryKey 的结果.
     * @mbg.generated
     */
    int updateByPrimaryKey(AuthRole record);

    /**
     * Description:  查询单条数据
     *
     * @param  example example
     * @return selectOneByExample 的结果.
     * @mbg.generated
     */
    AuthRole selectOneByExample(AuthRoleExample example);

    /**
     * Description:  查询单条数据字段选择性返回
     *
     * @param  example example
     * @param  selective selective
     * @return selectOneByExampleSelective 的结果.
     * @mbg.generated
     */
    AuthRole selectOneByExampleSelective(@Param("example") AuthRoleExample example, @Param("selective") AuthRole.Column ... selective);

    /**
     * Description:  批量插入
     *
     * @param  list list
     * @return batchInsert 的结果.
     * @mbg.generated
     */
    int batchInsert(@Param("list") List<AuthRole> list);

    /**
     * Description:  可选择字段批量插入
     *
     * @param  list list
     * @param  selective selective
     * @return batchInsertSelective 的结果.
     * @mbg.generated
     */
    int batchInsertSelective(@Param("list") List<AuthRole> list, @Param("selective") AuthRole.Column ... selective);

    /**
     * Description:  存在即更新(saveOrUpdate)
     *
     * @param  record record
     * @return upsert 的结果.
     * @mbg.generated
     */
    int upsert(AuthRole record);

    /**
     * Description:  存在即更新, 可选字段(saveOrUpdate)
     *
     * @param  record record
     * @return upsertSelective 的结果.
     * @mbg.generated
     */
    int upsertSelective(AuthRole record);
}