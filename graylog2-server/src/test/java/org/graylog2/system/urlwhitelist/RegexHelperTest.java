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
package org.graylog2.system.urlwhitelist;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class RegexHelperTest {
    RegexHelper regexHelper = new RegexHelper();

    @Test
    public void createRegexForTemplateUrl() {
        String url = "https://example.com/api/lookup?key=message_key&a=b&c=message_key&e=f";
        String template = "https://example.com/api/lookup?key=${key}&a=b&c=${key}&e=f";
        String expected = "^\\Qhttps://example.com/api/lookup?key=\\E.*?\\Q&a=b&c=\\E.*?\\Q&e=f\\E$";
        String got = regexHelper.createRegexForUrlTemplate(template, "${key}");
        assertThat(got).isEqualTo(expected);
        Pattern compiled = Pattern.compile(got, Pattern.DOTALL);
        assertThat(compiled.matcher(url).find()).isTrue();
    }

    @Test
    public void create() {
        String url = "https://example.com/api/lookup";
        String expected = "^\\Qhttps://example.com/api/lookup\\E$";
        String got = regexHelper.createRegexForUrl(url);
        assertThat(got).isEqualTo(expected);
        Pattern compiled = Pattern.compile(got, Pattern.DOTALL);
        assertThat(compiled.matcher(url).find()).isTrue();
    }
}
