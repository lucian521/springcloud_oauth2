package cn.com.taiji.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cn.com.taiji.catalog.CatalogInfo;
import cn.com.taiji.catalog.CatalogInfoRepository;

/**
 * @author zhuohao
 */
@Service
public class CatalogServiceV1 {
    @Autowired
    private CatalogInfoRepository catalogInfoRepository;

    @HystrixCommand
    public CatalogInfo getCatalog() {
        CatalogInfo activeCatalogInfo = catalogInfoRepository.findCatalogInfoByActive(true);
        return activeCatalogInfo;
    }
    
    @HystrixCommand
    public CatalogInfo getCatalog(String userid) {
        CatalogInfo activeCatalogInfo = catalogInfoRepository.findCatalogInfoByUserid(userid);
        return activeCatalogInfo;
    }

}
