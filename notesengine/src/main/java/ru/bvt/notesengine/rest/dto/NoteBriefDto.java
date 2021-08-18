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

import lombok.*;
import ru.bvt.notesengine.domain.Note;

/**
 * DTO that represents brief view of Note
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
public class NoteBriefDto {
    private String text;

    public static NoteBriefDto toDto(Note note) {
        return new NoteBriefDto(note.getText());
    }
}
