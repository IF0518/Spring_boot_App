package com.offer.requestOffer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRequestRepository extends PagingAndSortingRepository<PostRequest, Long> {
@Query(value= "SELECT * FROM postRequest WHERE MATCH(name,titleDescription,fullDescription) "
		+ "AGAINST (?1)", nativeQuery=true)
Page<PostRequest> search(String keyword, Pageable pageable);
}
