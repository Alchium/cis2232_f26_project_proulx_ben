# Fortnite Ranked System #

## Development Team ##

> **Business Client**:  _Nicholas Brown_	<br/>
> **Lead Developer**:  _Ben Proulx_	<br/>
> **Quality Control**:  _Kody Verhulp_	<br/>

---

## Description ##

> Build a Fortnite Ranked Tracker with a modern dashboard UI. Players can manually log matches, including eliminations, placement, wins, current rank, and rank progress. The application should automatically calculate statistics such as matches played, average eliminations, average placement, win rate, and overall ranked progression. Include match history, progress graphs, editable/deletable matches, persistent storage, and mobile responsiveness. Keep the code modular, organized, and easy to expand.

---

## Color ##

> Main Color:  Purple

## Required Fields ##
Field | Type | Description
--- | --- | ---
playerName	 |   String   |   Fortnite username of the player being tracked   <br/>
currentRank  |  String	 |   The players current ranked division    <br/>
rankProgress	| Double		 |   The players current percentage progress within their rank    <br/>
matchesPlayed	| Int		 |   Total number of ranked matches recorded.    <br/>
eliminations	| Int		 |   Total number of eliminations achieved    <br/>
wins	        | Int		 |   Total number of ranked matches won    <br/>
averagePlacement |	Double |   The player’s calculated average finishing position across matches    <br/>
lastUpdated	     |  String  |   The date and time when the player’s ranked statistics were last updated    <br/>

## Calculation ##

* Fields that require calculations 
    - RankProgress 
        * Calculation: New Rank Progress - Previous Rank Progress 
        * Example: Previous = 45% New = 62% 62 - 45 = +17% 
        * Shows how much ranked progress the player gained or lost. 
    - Average Eliminations 
        * Calculation: Total Eliminations ÷ Matches Played 
        * Example: 56 eliminations ÷ 20 matches = 2.8 eliminations per match 
    - Win Rate 
        * Calculation: (Wins ÷ Matches Played) × 100 
        * Example: 5 wins ÷ 20 matches × 100 = 25% win rate
    - Average Placement 
        * Calculation: Total Placement ÷ Matches Played 
        * Example: If total placement = 240 across 20 matches: 240 ÷ 20 = 12 
        * Average placement = 12th place 

* Fields that don't need calculations 
    * PlayerName → Used for identification 
    * CurrentRank → Stores the player's rank 
    * LastUpdated → Stores the date/time of the latest update 

Main calculations: Rank Change, Average Eliminations, Win Rate, and Average Placement.

---

## Report Details ##

> To be determined in future sprint