package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.Entity.ContributionPanel;
import com.example.cms.Entity.User;

public interface ContributionPanelRepository extends JpaRepository<ContributionPanel, Integer> {

         boolean  existsByPanelIdAndContributors(int panelId,User user);
}
