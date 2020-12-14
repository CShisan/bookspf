package cn.bookspf.mapper;

import java.util.ArrayList;

import cn.bookspf.model.Dto.SortDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import cn.bookspf.model.Dao.Sort;

@Mapper
@Repository
public interface SortMapper {

	/**
	 * 查询所有分类信息
	 * @return
	 */
	@Select("select * from sort")
	ArrayList<Sort> getSorts();

	/**
	 * 查询sortid
	 * @param sortid
	 * @return
	 */
	@Select("select sortid from sort where sortid=#{sortid} ")
	String findSortid(Integer sortid);

	/**
	 * 查询sortname(根据sortname)
	 * @param sortname
	 * @return
	 */
	@Select("select sortname from sort where sortname=#{sortname} ")
	String findSortname(String sortname);

	/**
	 * 查询sortname(根据sortid)
	 * @param sortid
	 * @return
	 */
	@Select("select sortname from sort where sortid=#{sortid}")
	String getSortname(Integer sortid);

	/**
	 * 添加分类
	 * @param sort
	 */
	@Insert("insert into sort values(#{sortid},#{sortname})")
	void addSort(SortDTO sort);

	/**
	 * 修改分类
	 * @param sort
	 */
	@Update("update sort set sortname=#{sortname} where sortid=#{sortid}")
	void alterSort(SortDTO sort);

	/**
	 * 删除分类
	 * @param sortid
	 */
	@Delete("delete from sort where sortid=#{sortid}")
	void deleteSort(Integer sortid);
}
