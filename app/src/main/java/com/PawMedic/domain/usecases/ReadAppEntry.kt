package com.PawMedic..domain.usecases

import com.PawMedic.data.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry (
    private  val localUserManager: LocalUserManager
) {
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}