# Fortnite Ranked System #

Sample cis2232 project

## Development Team ##

Business Client:  Nicholas Brown	<br/>
Lead Developer:  Ben Proulx	<br/>
Quality Control:  Kody Verhulp	<br/>

## Description ##

Build a Fortnite Ranked Tracker with a modern dashboard UI. Players can manually log matches, including eliminations, placement, wins, current rank, and rank progress. The application should automatically calculate statistics such as matches played, average eliminations, average placement, win rate, and overall ranked progression. Include match history, progress graphs, editable/deletable matches, persistent storage, and mobile responsiveness. Keep the code modular, organized, and easy to expand.
## Color ##

Main Color:  Purple

## Required Fields ##

matchNum	    String	<br/>
playerName	    String	<br/>
currentRank	    String	<br/>
rankProgress	int		<br/>
eliminations	Int		<br/>
gameWon	        String	<br/>

## Calculation ##

Fields that require calculations 
1. RankProgress · Calculation: New Rank Progress - Previous Rank Progress · Example: Previous = 45% New = 62% 62 - 45 = +17% · Shows how much ranked progress the player gained or lost. 
2. Average Eliminations · Calculation: Total Eliminations ÷ Matches Played · Example: 56 eliminations ÷ 20 matches = 2.8 eliminations per match 
3. Win Rate · Calculation: (Wins ÷ Matches Played) × 100 · Example: 5 wins ÷ 20 matches × 100 = 25% win rate
4. Average Placement · Calculation: Total Placement ÷ Matches Played · Example: If total placement = 240 across 20 matches: 240 ÷ 20 = 12 · Average placement = 12th place 

Fields that don't need calculations · PlayerName → Used for identification · CurrentRank → Stores the player's rank · LastUpdated → Stores the date/time of the latest update 

Main calculations: Rank Change, Average Eliminations, Win Rate, and Average Placement.

## Report Details ##

To be determined in future sprint