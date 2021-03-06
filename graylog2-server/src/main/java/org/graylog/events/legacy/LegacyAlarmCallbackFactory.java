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
package org.graylog.events.legacy;

import com.google.inject.Injector;
import org.graylog2.plugin.alarms.callbacks.AlarmCallback;
import org.graylog2.plugin.alarms.callbacks.AlarmCallbackConfigurationException;
import org.graylog2.plugin.configuration.Configuration;

import javax.inject.Inject;
import java.util.Map;
import java.util.Set;

public class LegacyAlarmCallbackFactory {
    private Injector injector;
    private final Set<Class<? extends AlarmCallback>> availableAlarmCallbacks;

    @Inject
    public LegacyAlarmCallbackFactory(Injector injector,
                                      Set<Class<? extends AlarmCallback>> availableAlarmCallbacks) {
        this.injector = injector;
        this.availableAlarmCallbacks = availableAlarmCallbacks;
    }

    public AlarmCallback create(String type, Map<String, Object> configuration) throws ClassNotFoundException, AlarmCallbackConfigurationException {
        AlarmCallback alarmCallback = create(type);
        alarmCallback.initialize(new Configuration(configuration));

        return alarmCallback;
    }

    private AlarmCallback create(String type) throws ClassNotFoundException {
        for (Class<? extends AlarmCallback> availableClass : availableAlarmCallbacks) {
            if (availableClass.getCanonicalName().equals(type)) {
                return create(availableClass);
            }
        }
        throw new RuntimeException("No class found for type " + type);
    }

    private AlarmCallback create(Class<? extends AlarmCallback> alarmCallbackClass) {
        return injector.getInstance(alarmCallbackClass);
    }
}
