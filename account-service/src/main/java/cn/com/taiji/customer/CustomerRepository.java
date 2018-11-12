package cn.com.taiji.customer;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 为{@link Customer}领域类提供包括分页和排序等基本的管理能力
 * @author zhuohao
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

}
