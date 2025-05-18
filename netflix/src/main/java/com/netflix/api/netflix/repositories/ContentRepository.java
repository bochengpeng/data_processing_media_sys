package com.netflix.api.netflix.repositories;

import com.netflix.api.netflix.models.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer>
{
}
