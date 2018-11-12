package cn.com.taiji.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * {@link CatalogInfo}领域类提供基于关系数据库的管理能力
 * @author zhuohao
 */
public interface CatalogInfoRepository extends JpaRepository<CatalogInfo, String> {
    CatalogInfo findCatalogInfoByActive(@Param("active") Boolean active);
    
    CatalogInfo findCatalogInfoByUserid(@Param("userid") String userid);
}
