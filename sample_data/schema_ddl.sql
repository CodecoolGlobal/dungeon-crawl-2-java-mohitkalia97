ALTER TABLE IF EXISTS ONLY public.game_state DROP CONSTRAINT IF EXISTS fk_player_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.items DROP CONSTRAINT IF EXISTS fk_player_id CASCADE;

DROP TABLE IF EXISTS public.game_state;
DROP TABLE IF EXISTS public.inventory;
DROP TABLE IF EXISTS public.items;


CREATE TABLE public.game_state (
    id serial NOT NULL PRIMARY KEY,
    current_map text NOT NULL,
    saved_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    player_id integer NOT NULL
);

DROP TABLE IF EXISTS public.player;
CREATE TABLE public.player (
    id serial NOT NULL PRIMARY KEY,
    player_name text NOT NULL,
    hp integer NOT NULL,
    x integer NOT NULL,
    y integer NOT NULL
);

CREATE TABLE public.items(
    player_id INTEGER NOT NULL,
    name VARCHAR(64) NOT NULL
);


ALTER TABLE ONLY public.game_state
    ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES public.player(id);

ALTER TABLE ONLY public.items
    ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES public.player(id);