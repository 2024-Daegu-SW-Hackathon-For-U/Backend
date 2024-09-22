-- Map 테이블에 데이터 삽입
INSERT INTO map (id, name, type, count) VALUES (1, '팔공산', 'PARK', 0);
INSERT INTO map (id, name, type, count) VALUES (2, 'Historical Sites', 'HISTORY', 0);
INSERT INTO map (id, name, type, count) VALUES (3, 'Nature Trails', 'NATURE', 0);

-- Place 테이블에 데이터 삽입
INSERT INTO place (id, name, description) VALUES (10821532, "대구이월드", "대구 달서구 두류공원로 200", 35.85367145505638, 128.56471271337392);
INSERT INTO place (id, name, description) VALUES (27464044, "서문시장", "대구 중구 큰장로26길 45 (우)41926", 35.868908632375664, 128.58077341965037);
INSERT INTO place (id, name, description) VALUES (11343455, "달성공원", "대구 중구 달성공원로 35", 35.8602851249823, 128.577757377213);
INSERT INTO place (id, name, description) VALUES (14600118, "김광석다시그리기길", "대구 중구 대봉동 6-11", 35.8740343528945, 128.6069533801216);
INSERT INTO place (id, name, description) VALUES (15174531, "대구앞산공원", "대구 남구 대명동 산 227-5", 35.82721640685348, 128.58765535374687);
INSERT INTO place (id, name, description) VALUES (14600118, "수성못", "대구 수성구 두산동 539", 35.82770180872425, 128.61727641474653);
INSERT INTO place (id, name, description) VALUES (14600118, "김광석다시그리기길", "대구 중구 대봉동 6-11", 35.8740343528945, 128.6069533801216);
INSERT INTO place (id, name, description) VALUES (14600118, "김광석다시그리기길", "대구 중구 대봉동 6-11", 35.8740343528945, 128.6069533801216);


-- PlaceInMap 테이블에 데이터 삽입
INSERT INTO place_in_map (id, map_id, place_id) VALUES (1, 1, 1); -- City Park에 Central Fountain
INSERT INTO place_in_map (id, map_id, place_id) VALUES (2, 2, 2); -- Historical Sites에 Old Castle
INSERT INTO place_in_map (id, map_id, place_id) VALUES (3, 3, 3); -- Nature Trails에 Mountain View Trail
INSERT INTO place_in_map (id, map_id, place_id) VALUES (4, 1, 4); -- City Park에 Rose Garden
INSERT INTO place_in_map (id, map_id, place_id) VALUES (5, 2, 1); -- Historical Sites에 Central Fountain
