/**
 * This file is part of Graylog.
 *
 * Graylog is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog2.rest.models.system.loggers.responses;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import org.graylog.autovalue.WithBeanGetter;

import java.util.List;

@AutoValue
@WithBeanGetter
@JsonAutoDetect
public abstract class SingleSubsystemSummary {
    @JsonProperty
    public abstract String title();
    @JsonProperty
    public abstract List<String> categories();
    @JsonProperty
    public abstract String description();
    @JsonProperty
    public abstract String level();
    @JsonProperty("level_syslog")
    public abstract int levelSyslog();

    @JsonCreator
    public static SingleSubsystemSummary create(@JsonProperty("title") String title,
                                                @JsonProperty("categories") List<String> categories,
                                                @JsonProperty("description") String description,
                                                @JsonProperty("level") String level,
                                                @JsonProperty("level_syslog") int levelSyslog) {
        return new AutoValue_SingleSubsystemSummary(title, categories, description, level, levelSyslog);
    }
}
