set search_path to design_style;

insert into app_users(first_name, last_name, email, username, password)
values
    ('John', 'Doe', 'jdpower@gmail.com', 'jd99', 'pass420blazeit'),
    ('Peter', 'Piper', 'ppp@loan.com', 'scam99', 'jailtonight'),
    ('Kanye', 'West', 'church@gmail.com', 'ye270', 'yzyboost270'),
    ('Engelbert', 'Humperdinck', 'cantpronounce@gmail.com', 'english', 'spellcheck99');

insert into style_category (name, description, creator_id)
values 
    ('Contemporary', 'Always evolving', 3), 
    ('Traditional', '18th Century England', 4), 
    ('Coastal', 'Natural environment', 2), 
    ('Rustic', 'Simple and effortless', 4), 
    ('Industrial', 'Use of brick and concrete', 3);

insert into design (style, design_category, creator_id)
values    
    ('https://unsplash.com/photos/Pf-GjKfYGO4', 3, 2),
    ('https://unsplash.com/photos/rYZkQdz2t9o', 4, 1),
    ('https://unsplash.com/photos/khpWE85ge38', 1, 2),
    ('https://unsplash.com/photos/x6vyL4YKP9c', 5, 3),
    ('https://unsplash.com/photos/NVjDKvGXsuA', 2, 4);
    ('https://unsplash.com/photos/T6d96Qrb5MY', 1, 3);

--QUERIES

select * from app_users;

select * from design 

select * from design where design_category = 2;

select * from design where creator_id = 3;

select * from style_category;