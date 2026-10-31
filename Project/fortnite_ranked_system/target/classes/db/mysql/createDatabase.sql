# Initial S1 db setup

DROP DATABASE IF EXISTS cis2232_fortnite_ranked_system;
CREATE DATABASE cis2232_fortnite_ranked_system;
use cis2232_fortnite_ranked_system;

CREATE TABLE match_list (
                              matchId           INT             NOT NULL AUTO_INCREMENT,
                              playerName        VARCHAR(100)    NOT NULL,
                              currentRank       VARCHAR(20)     NOT NULL,
                              rankProgress      DECIMAL(5,2)    NOT NULL,
                              eliminations      INT             NOT NULL,
                              win               BOOL            NOT NULL,
                              lastUpdated       VARCHAR(100)    NOT NULL,
                              PRIMARY KEY (matchId)
);

INSERT INTO match_list
(playerName, currentRank, rankProgress, eliminations, win, lastUpdated)

# Initial sample data
VALUES
    ('Player1', 'Silver', 23,  25, true, '2026-09-28 15:10:56'),
    ('Player1', 'Gold', 99, 43, true, '2026-09-23 15:10:56'),
    ('Player1', 'Bronze', 0, 3, false,  '2026-08-10 15:10:56'),
    ('Player2', 'Bronze', 23,  25, true, '2026-09-28 15:10:56'),
    ('Player3', 'Gold', 12, 4, false, '2026-09-23 15:10:56'),
    ('Player2', 'Gold', 0, 3, false,  '2026-08-10 15:10:56')
;

