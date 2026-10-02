# Initial S1 db setup

DROP DATABASE IF EXISTS cis2232_fortnite_tracker;
DROP DATABASE IF EXISTS cis2232_fortnite_ranked_system;
CREATE DATABASE cis2232_fortnite_ranked_system;
use cis2232_fortnite_ranked_system;

DROP TABLE IF EXISTS player_table;

CREATE TABLE player_table (
                              id                INT             NOT NULL AUTO_INCREMENT,
                              playerName        VARCHAR(100)    NOT NULL,
                              currentRank       VARCHAR(20)     NOT NULL,
                              rankProgress      DECIMAL(5,2)    NOT NULL,
                              matchesPlayed     INT             NOT NULL,
                              eliminations      INT             NOT NULL,
                              wins              INT             NOT NULL,
                              averagePlacement  DECIMAL(5,2)    NOT NULL,
                              lastUpdated       VARCHAR(100)    NOT NULL,
                              PRIMARY KEY (id)
);

INSERT INTO player_table
(playerName, currentRank, rankProgress, matchesPlayed, eliminations, wins, averagePLacement, lastUpdated)

# Initial sample data
VALUES
    ('Player1', 'Silver', 23, 5, 25, 2, 2.3, '2026-09-28 15:10:56'),
    ('Player2', 'Gold', 99, 167, 403, 120, 1.2, '2026-09-23 15:10:56'),
    ('Player3', 'Bronze', 0, 1, 3, 0, 60, '2026-08-10 15:10:56')
;