package cn.itcast.dao;

import cn.itcast.entity.Linkman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LinkmanDao extends JpaRepository<Linkman,Long>,JpaSpecificationExecutor<Linkman> {
}
