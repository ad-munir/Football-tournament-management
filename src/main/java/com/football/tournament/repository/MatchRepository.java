package com.football.tournament.repository;

import com.football.tournament.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match,Long> {

    List<Match> findByDateMatch(Date date);

    @Modifying
    @Query("DELETE FROM Match WHERE dateMatch < current_date")
    int deleteFinishedMatches();


    int deleteByDateMatchBeforeAndHeureMatchBefore(Date dateMatch, Time heureMatch);

//    List<Match> findByDateMatchLessThanOrDateMatchEqualsAndHeureMatchLessThan(Date dateMatch, Time heureMatch);

    List<Match> findByDateMatchBefore(Date date);
}
