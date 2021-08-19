/*
 * Copyright 2016 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right name only.
 */
package ru.bvt.notesengine.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bvt.notesengine.domain.Category;
import ru.bvt.notesengine.domain.Note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DTO that represents standart view of Note with primary attributes
 * <p>
 * Описание используемых DTO для заметок:
 * NoteFullDto - для вывода полного списка параметров DTO
 * NoteBriefDto - для выстрого создания заметки из телеграм
 * только по тексту заметки (чтобы не тащить всю простыню
 * параметров в сторонние приложения)
 * NoteDto - для штатных Create/Update операций (для заметок
 * даётся возможность изменять только текст заметки
 * и номер книги в которой она содержится,
 * а автор и категория подставляются автоматически)
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {
    private String text;
    private long bookId;

}
