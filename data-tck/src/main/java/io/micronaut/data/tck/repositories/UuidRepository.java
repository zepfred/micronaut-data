/*
 * Copyright 2017-2021 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.data.tck.repositories;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.tck.entities.UuidEntity;

import java.util.Collection;
import java.util.UUID;

public interface UuidRepository extends CrudRepository<UuidEntity, UUID> {

    UUID findUuidByName(String name);

    /**
     * This method semantically makes no sense because it'll always produce NULL
     * without correctly wrapping nullable value like this:
     * WHERE :nullableValue IS NULL AND nullable_value IS NULL
     *       OR
     *       :nullableValue IS NOT NULL AND nullable_value = :nullableValue
     */
    @Query(
        // value = "select * from uuid_entity where (:param)\\:\\:uuid is null",
        value = "select * from uuid_entity where :param is null",
        nativeQuery = true
    )
    Collection<UuidEntity> findByNullableValue(@Nullable UUID param);
}
