CREATE TABLE teams (
    team_name varchar(30) not null,
    association varchar(10) not null check (upper(association) = association),
    fifa_rank number(3) not null,
    pot number(1) not null,
    team_group varchar(1) not null check (team_group between 'A' and 'H'),
    CONSTRAINT team_name_pk PRIMARY KEY (team_name),
    CONSTRAINT team_rank_uk UNIQUE (fifa_rank)
);


CREATE TABLE players (
    player_id number(3) not null,
    player_number number(2) not null check (player_number between 1 and 26),
    player_pos varchar(2) not null check (player_pos in ('GK', 'DF', 'MF', 'FW')),
    player_first_name varchar(15) not null,
    player_last_name varchar(15) not null,
    player_goals number(3),
    date_of_birth date,
    club varchar(50),
    team_name varchar(30) not null,
    CONSTRAINT player_id_pk PRIMARY KEY (player_id),
    CONSTRAINT player_team_fk FOREIGN KEY (team_name) references teams(team_name)
);

CREATE TABLE player_stats (
    player_id number(3) not null,
    games_played number(1),
    minutes_played number(4),
    goals number(2),
    assists number(2),
    shots number(2),
    yellow_cards number(1),
    red_cards number(1),
    CONSTRAINT player_id_fk FOREIGN KEY (player_id) references players(player_id)
);

CREATE TABLE stadiums (
    stadium_id number(1) not null,
    stadium_name varchar(30) not null,
    location varchar(30) not null,
    capacity number(6) not null,
    games number(10) not null,
    CONSTRAINT stadium_id_pk PRIMARY KEY (stadium_id)
);