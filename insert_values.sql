insert into teams(team_name, association, fifa_rank, pot, team_group) values('Argentina', 'CONMEBOL', 3, 1, 'C');
insert into teams(team_name, association, fifa_rank, pot, team_group) values('Australia', 'AFC', 38, 4, 'D');
insert into teams(team_name, association, fifa_rank, pot, team_group) values('Belgium', 'UEFA', 2, 1, 'F');
insert into teams(team_name, association, fifa_rank, pot, team_group) values('Japan', 'AFC', 24, 3, 'E');
insert into teams(team_name, association, fifa_rank, pot, team_group) values('Denmark', 'UEFA', 11, 2, 'D');
insert into teams(team_name, association, fifa_rank, pot, team_group) values('Ecuador', 'CONMEBOL', 46, 4, 'A');
insert into teams(team_name, association, fifa_rank, pot, team_group) values('United States', 'CONCACAF', 15, 2, 'B');
insert into teams(team_name, association, fifa_rank, pot, team_group) values('Germany', 'UEFA', 12, 2, 'E');
insert into teams(team_name, association, fifa_rank, pot, team_group) values('Portugal', 'UEFA', 8, 1, 'H');

insert into players(player_id, player_number, player_pos, player_first_name, player_last_name, player_goals, date_of_birth, club, team_name)
            values(4, 4, 'DF', 'Robert', 'Arboleda', 2, '22-OCT-1991', 'São Paulo', 'Ecuador');
insert into players(player_id, player_number, player_pos, player_first_name, player_last_name, player_goals, date_of_birth, club, team_name)
            values(177, 21, 'FW', 'Timothy', 'Weah', 3, '22-FEB-2000', 'Lille', 'United States');
insert into players(player_id, player_number, player_pos, player_first_name, player_last_name, player_goals, date_of_birth, club, team_name)
            values(157, 1, 'GK', 'Matt', 'Turner', NULL, '24-06-1994', 'Arsenal', 'United States');
insert into players(player_id, player_number, player_pos, player_first_name, player_last_name, player_goals, date_of_birth, club, team_name)
            values(218, 10, 'FW', 'Lionel', 'Messi', 91, '24-06-1987', 'Paris Saint-Germain', 'Argentina');
insert into players(player_id, player_number, player_pos, player_first_name, player_last_name, player_goals, date_of_birth, club, team_name)
            values(449, 7, 'FW', 'Kai', 'Havertz', 10, '11-06-1999', 'Chelsea', 'Germany');
            
insert into player_stats(player_id, games_played, minutes_played, goals, assists, shots, yellow_cards, red_cards)
            values(218, 7, 690, 7, 3, 31, 1, NULL);
insert into player_stats(player_id, games_played, minutes_played, goals, assists, shots, yellow_cards, red_cards)
            values(449, 2, 103, 2, NULL, 4, NULL, NULL);
insert into player_stats(player_id, games_played, minutes_played, goals, assists, shots, yellow_cards, red_cards)
            values(157, 4, 360, NULL, NULL, NULL, NULL, NULL);
insert into player_stats(player_id, games_played, minutes_played, goals, assists, shots, yellow_cards, red_cards)
            values(4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
insert into player_stats(player_id, games_played, minutes_played, goals, assists, shots, yellow_cards, red_cards)
            values(177, 4, 316, 1, NULL, 2, NULL, NULL);
            
insert into stadiums(stadium_id, stadium_name, location, capacity, games)
            values(1, 'Al Bayt Stadium', 'Al Khor', 68895, 9);
insert into stadiums(stadium_id, stadium_name, location, capacity, games)
            values(2, 'Lusail Stadium', 'Lusail', 88966, 10);
insert into stadiums(stadium_id, stadium_name, location, capacity, games)
            values(3, 'Al Janoub Stadium', 'Al Wakrah', 44325, 7);
insert into stadiums(stadium_id, stadium_name, location, capacity, games)
            values(4, 'Ahmad Bin Ali Stadium', 'Al Rayyan', 45032, 7);
insert into stadiums(stadium_id, stadium_name, location, capacity, games)
            values(5, 'Khalifa International Stadium', 'Al Rayyan', 45857, 8);