/*
 * Copyright (C) 2016 Álinson Santos Xavier <isoron@gmail.com>
 *
 * This file is part of Loop Habit Tracker.
 *
 * Loop Habit Tracker is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Loop Habit Tracker is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.isoron.uhabits.core.models

import org.isoron.uhabits.core.database.*
import org.isoron.uhabits.core.models.sqlite.records.*

/**
 * Interface implemented by factories that provide concrete implementations of
 * the core model classes.
 */
interface ModelFactory {

    fun buildHabit(): Habit {
        val computedEntries = buildEntryList()
        val scores = buildScoreList()
        val streaks = buildStreakList()
        val habit = Habit(
                computedEntries = computedEntries,
                scores = scores,
                streaks = streaks,
                originalEntries = buildOriginalEntries(),
        )
        computedEntries.setHabit(habit)
        scores.setHabit(habit)
        streaks.setHabit(habit)
        return habit
    }

    fun buildOriginalEntries(): Entries
    fun buildEntryList(): EntryList
    fun buildHabitList(): HabitList
    fun buildScoreList(): ScoreList
    fun buildStreakList(): StreakList
    fun buildHabitListRepository(): Repository<HabitRecord>
    fun buildRepetitionListRepository(): Repository<EntryRecord>
}