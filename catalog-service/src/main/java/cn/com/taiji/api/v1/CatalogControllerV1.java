package cn.com.taiji.api.v1;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.taiji.catalog.CatalogInfo;

/**
 * @author zhuohao
 */
@RestController
@RequestMapping("/v1")
public class CatalogControllerV1 {
    @Autowired
    private CatalogServiceV1 catalogService;

    @RequestMapping(value = "/catalog", method = RequestMethod.GET, name = "getCatalog")
    public ResponseEntity<CatalogInfo> getCatalog() {
        return Optional.ofNullable(catalogService.getCatalog())
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @RequestMapping(value = "/catalogbyuser/{userid}", method = RequestMethod.GET, name = "getCatalogbyuser")
    public ResponseEntity<CatalogInfo> getProduct(@PathVariable("userid") String userid) {
        return Optional.ofNullable((catalogService.getCatalog(userid)))
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
