--INSERT INTO public.korisnik(adresa, datum_rodjenja, email, ime, kor_ime, lozinka, prezime, status_naloga, tip_korisnika) VALUES ('Sindjeliceva 22 Becej', '2022-04-14', 'temp@gmail.com', 'zdravko', 'tempUsernamae', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', 'Todovski', 0, 1);
--INSERT INTO public.korisnik(adresa, datum_rodjenja, email, ime, kor_ime, lozinka, prezime, status_naloga, tip_korisnika) VALUES ('Arsenija Carnojevica 1 Becej', '1996-04-14', 'arsen@gmail.com', 'Mara', 'marica', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', 'Markovic', 1, 0);
--INSERT INTO public.korisnik(adresa, datum_rodjenja, email, ime, kor_ime, lozinka, prezime, status_naloga, tip_korisnika) VALUES ('Nemanjina 11 Beograd', '2022-04-14', 'smajser@gmail.com', 'Slobodan', 'slobo', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', 'Misovic', 0, 2);
--INSERT INTO public.korisnik(adresa, datum_rodjenja, email, ime, kor_ime, lozinka, prezime, status_naloga, tip_korisnika) VALUES ('Svetozara Markovica 7 Nis', '2022-04-14', 'temp2@gmail.com', 'Milos', 'snp1389', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', 'Markovic', 0, 2);
--INSERT INTO public.korisnik(adresa, datum_rodjenja, email, ime, kor_ime, lozinka, prezime, status_naloga, tip_korisnika) VALUES ('Omladinska 10 Koceljeva Srbija', '1996-05-22', 'npele96@gmail.com', 'Nikola', 'pele96', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', 'Petkovic', 0, 2);
--INSERT INTO public.korisnik(adresa, datum_rodjenja, email, ime, kor_ime, lozinka, prezime, status_naloga, tip_korisnika) VALUES ('Lasla Gala 28 Novi Sad Srbija', '1999-03-21', 'acpele96@gmail.com', 'Nikolina', 'niPet', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', 'Petko', 0, 1);

--INSERT INTO public.adresa(id, adresa, lat, lng) VALUES (1, 'Novi Sad, Maksima Gorkor 12', 10.22, 52.02002);

--ID je polje koje se generise automatski i ne treba ga navoditi ovde jer se brojac zbuni

--ADRESE
INSERT INTO public.adresa(adresa, lat, lng) VALUES ('Novi Sad, Maksima Gorkog 12', 10.22, 52.02002);
INSERT INTO public.adresa(adresa, lat, lng) VALUES ('Kladovo, Omladinska 6', 55.122, 25.95625);
INSERT INTO public.adresa(adresa, lat, lng) VALUES ('Beograd, Svetozara Markovica 1', 26.425, 24.623634);
INSERT INTO public.adresa(adresa, lat, lng) VALUES ('Subotica, Jovana Nenada BB', 17.56236, 26.536322);


--KORISNICI
INSERT INTO public.korisnik(email, ime, kor_ime, lozinka, prezime, status_naloga, tip_korisnika, adresa_id) VALUES ('temp@gmail.com', 'zdravko', 'tempUsername', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', 'Todovski', 0, 1, 1);
INSERT INTO public.korisnik(email, ime, kor_ime, lozinka, prezime, status_naloga, tip_korisnika, adresa_id) VALUES ('arsen@gmail.com', 'Mitar', 'mitar22', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', 'Mitra', 0, 1, 2);
INSERT INTO public.korisnik(email, ime, kor_ime, lozinka, prezime, status_naloga, tip_korisnika, adresa_id) VALUES ('smajser@gmail.com', 'Slobodan', 'sloba', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', 'Misovic', 0, 1, 3);
INSERT INTO public.korisnik(email, ime, kor_ime, lozinka, prezime, status_naloga, tip_korisnika, adresa_id) VALUES ('npele96@gmail.com', 'Nikola', 'npele96', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', 'Petkovic', 0, 2, 4);

INSERT INTO public.guest(broj_otkazivanja, id)	VALUES (0, 1);
INSERT INTO public.guest(broj_otkazivanja, id)	VALUES (5, 2);
INSERT INTO public.guest(broj_otkazivanja, id)	VALUES (0, 3);

--INSERT INTO public.host(istaknuti, id) VALUES (false, 4);
INSERT INTO public.host(istaknuti, rez_automatski, id) VALUES (false, false, 4);


INSERT INTO public.pogodnost(naziv) VALUES ('air conditioning');
INSERT INTO public.pogodnost(naziv) VALUES ('room service');
INSERT INTO public.pogodnost(naziv) VALUES ('hairdresser');
INSERT INTO public.pogodnost(naziv) VALUES ('gym');
INSERT INTO public.pogodnost(naziv) VALUES ('luggage locker');
INSERT INTO public.pogodnost(naziv) VALUES ('safety deposit box');
INSERT INTO public.pogodnost(naziv) VALUES ('doctor on call');
