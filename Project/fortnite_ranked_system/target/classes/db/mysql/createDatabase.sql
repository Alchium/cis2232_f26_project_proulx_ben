# Initial S1 db setup

DROP DATABASE IF EXISTS cis2232_fortnite_ranked_system;
CREATE DATABASE cis2232_fortnite_ranked_system;
use cis2232_fortnite_ranked_system;

CREATE TABLE MatchEntry (
                              matchId           INT             NOT NULL AUTO_INCREMENT,
                              playerName        VARCHAR(100)    NOT NULL,
                              currentRank       VARCHAR(20)     NOT NULL,
                              rankProgress      DECIMAL(5,2)    NOT NULL,
                              eliminations      INT             NOT NULL,
                              placement         INT             NOT NULL,
                              win               BOOL            NOT NULL,
                              matchDate         VARCHAR(10)     NOT NULL,
                              matchTime         VARCHAR(10)     NOT NULL,
                              PRIMARY KEY (matchId)
);

INSERT INTO MatchEntry
(playerName, currentRank, rankProgress, eliminations, placement, win, matchDate, matchTime)

# Initial sample data
VALUES
    ('Player1', 'Silver', 23,  25, 1, true, '2026-09-28','15:10:56'),
    ('Player1', 'Gold', 99, 43, 1, true, '2026-09-23','15:10:56'),
    ('Player1', 'Bronze', 0, 3, 93, false, '2026-08-10','15:10:56'),
    ('Player2', 'Bronze', 23,  25, 1, true, '2026-09-28','15:10:56'),
    ('Player3', 'Gold', 12, 4, 56, false, '2026-09-23','15:10:56'),
    ('Player2', 'Gold', 0, 3, 87, false,  '2026-08-10','15:10:56'),
    ('Player1', 'Bronze', 23,  25, 1, true, '2026-09-28','15:10:56'),
    ('Player3', 'Gold', 16, 12, 3, false, '2026-09-23','15:10:56'),
    ('Player1', 'Gold', 30, 9, 23, false,  '2026-08-10','15:10:56'),
    ('Player2', 'Bronze', 23,  25, 1, true, '2026-09-28','15:10:56'),
    ('Player3', 'Gold', 82, 14, 1, true, '2026-09-23','15:10:56'),
    ('Player2', 'Gold', 1, 3, 1, true,  '2026-08-10','15:10:56')
;


# Adding old table back in temporarily - this will be removed once
# we get to sprint 3.


CREATE TABLE SkillsAssessmentSquashTechnical
(
    id                int(5),
    assessmentDate    varchar(10) NOT NULL COMMENT 'yyyy-MM-dd',
    createdDateTime   varchar(20) NOT NULL COMMENT 'yyyy-MM-dd hh:mm:ss',
    athleteName       varchar(50) NOT NULL COMMENT 'Athletes name',
    assessorName      varchar(50) NOT NULL COMMENT 'Athletes name',
    forehandDrives    int(5) COMMENT 'Number of forehand drives',
    backhandDrives    int(5) COMMENT 'Number of backhand drives',
    forehandVolleyMax int(5) COMMENT 'Max number of forehand volleys',
    forehandVolleySum int(5) COMMENT 'Sum of forehand volleys',
    backhandVolleyMax int(5) COMMENT 'Max number of backhand volleys',
    backhandVolleySum int(5) COMMENT 'Sum of backhand volleys',
    technicalScore    int(5) COMMENT 'Score calculated at submission'
) COMMENT 'This table holds technical skills assessment details';

ALTER TABLE SkillsAssessmentSquashTechnical
    ADD PRIMARY KEY (id);
ALTER TABLE SkillsAssessmentSquashTechnical
    MODIFY id int(4) NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key',
    AUTO_INCREMENT = 1;