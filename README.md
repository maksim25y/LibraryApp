Данный проект я выполнял при изучении Hibernate/Spring Core/Spring Data Jpa, представляет из себя веб-приложение (система учета книг в бибилиотеке).

<details><summary>Функционал</summary>
На главной странице расположен блок с кнопками и хэдер. При нажатии на  кнопку "Список людей в библиотеке" происходит переход на страницу (/people) со списком людей в библиотеке.

![image](https://github.com/maksim25y/LibraryApp/assets/131711956/29066f2d-005f-4756-ae76-6a87ceae58cf)
Есть возможность добавления человека в базу данных библиотеки, необходимо нажать на кнопку "Добавить", после чего происходит переход на страницу с адресом (/people/new).

![image](https://github.com/maksim25y/LibraryApp/assets/131711956/16966b58-36cc-4759-b8d6-a47e9a0ec346)
Поля в форме имеют валидацию, поэтому ввести некорректные значения нельзя (в таком случае будет выведена ошибка).
Ограничения на ввод: 
1) Возраст должен быть в следующем диапазоне (1900-2024]

![image](https://github.com/maksim25y/LibraryApp/assets/131711956/5db855fa-c952-46a4-8830-a0d983229f9b)
2) ФИО должно быть по следующему шаблону [Фамилия Имя Отчество], причем на русском языке

![image](https://github.com/maksim25y/LibraryApp/assets/131711956/42998745-7f27-4bc6-9646-85f67ae6363f)
3) Поля не должны быть пустыми
4) ФИО должно быть уникальным

![image](https://github.com/maksim25y/LibraryApp/assets/131711956/f6bd633b-d37c-4da5-82ac-11b052afd177)

![image](https://github.com/maksim25y/LibraryApp/assets/131711956/a499732b-51bf-4543-9e48-d4ecd792c1a1)


  
![image](https://github.com/maksim25y/LibraryApp/assets/131711956/10e54254-b1b5-4301-8eb1-b0579f915f44)

<details><summary>Реализация</summary>  
  
![image](https://github.com/maksim25y/http1/assets/131711956/e10d065f-6ccf-455c-92f8-0a5315de7f66)

</details>
