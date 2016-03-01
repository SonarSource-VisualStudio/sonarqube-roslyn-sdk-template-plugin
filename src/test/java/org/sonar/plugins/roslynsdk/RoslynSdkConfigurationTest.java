/*
 * SonarQube Roslyn SDK Template Plugin
 * Copyright (C) 2016-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.plugins.roslynsdk;

import com.google.common.collect.ImmutableMap;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.fest.assertions.Assertions.assertThat;

public class RoslynSdkConfigurationTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void invalid_xml() {
    thrown.expect(IllegalStateException.class);
    thrown.expectMessage("Invalid Roslyn SDK XML configuration file: ");
    thrown.expectMessage("invalid_xml.xml");

    new RoslynSdkConfiguration("/RoslynSdkConfigurationTest/invalid_xml.xml");
  }

  @Test
  public void invalid_root() {
    thrown.expect(IllegalStateException.class);
    thrown.expectMessage("Expected <RoslynSdkConfiguration> as root element in configuration file:");
    thrown.expectMessage("invalid_root.xml");
    thrown.expectMessage("<foo>");

    new RoslynSdkConfiguration("/RoslynSdkConfigurationTest/invalid_root.xml");
  }

  @Test
  public void valid() {
    RoslynSdkConfiguration config = new RoslynSdkConfiguration("/RoslynSdkConfigurationTest/valid.xml");
    assertThat(config.mandatoryProperty("Foo")).isEqualTo("FooValue");
    assertThat(config.property("Foo")).isEqualTo("FooValue");
    assertThat(config.mandatoryProperty("Bar")).isEqualTo("BarValue");
    assertThat(config.property("Bar")).isEqualTo("BarValue");

    assertThat(config.property("NonExisting")).isNull();

    assertThat(config.properties()).isEqualTo(ImmutableMap.of(
      "Foo", "FooValue",
      "Bar", "BarValue"));

    assertThat(config.pluginProperties()).isEqualTo(ImmutableMap.of(
      "PluginFoo", "PluginFooValue",
      "PluginBar", "PluginBarValue"));
  }

  @Test
  public void missing_mandatory() {
    thrown.expect(IllegalStateException.class);
    thrown.expectMessage("Mandatory <MissingPropertyKey> element not found in the Roslyn SDK XML configuration file: ");
    thrown.expectMessage("valid.xml");

    RoslynSdkConfiguration config = new RoslynSdkConfiguration("/RoslynSdkConfigurationTest/valid.xml");
    config.mandatoryProperty("MissingPropertyKey");
  }

  @Test
  public void empty() {
    RoslynSdkConfiguration config = new RoslynSdkConfiguration("/RoslynSdkConfigurationTest/empty.xml");
    assertThat(config.properties()).isEmpty();
    assertThat(config.pluginProperties()).isEmpty();
  }

  @Test
  public void default_path() {
    thrown.expectMessage("/org/sonar/plugins/roslynsdk/configuration.xml");
    new RoslynSdkConfiguration();
  }

}
