
/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package core.Persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The application settings.
 *
 * @author Paulo Gandra Sousa
 */
public class AppSettings {
    private static final Logger LOGGER = LogManager.getLogger(AppSettings.class);

    private static final String PROPERTIES_RESOURCE = "application.properties";
    private static final String REPOSITORY_FACTORY_KEY = "persistence.repositoryFactory";
    private static final String UI_MENU_LAYOUT_KEY = "ui.menu.layout";
    private static final String PERSISTENCE_UNIT_KEY = "persistence.persistenceUnit";
    private static final String SCHEMA_GENERATION_KEY = "jakarta.persistence.schema-generation.database.action";
    private static final String USE_EVENTFUL_CONTROLLERS = "UseEventfulControllers";
    private static final String SHODRONE_DOMAIN = "ShodroneDomain";
    private static final String SERVER_PORT = "shodrone.server.port";
    private static final String DATABASE_PORT = "shodrone.database.port";
    private static final String SERVER_IP = "shodrone.server.ip";
    private static final String REPORT_PATH = "simulation.report.path";
    private static final String TEMPLATE_PATH_PORTUGUESE = "templates.proposal.portuguese";
    private static final String TEMPLATE_PATH_ENGLISH = "templates.proposal.englishRegular";
    private static final String TEMPLATE_PATH_ENGLISH_VIP = "templates.proposal.englishVIP";
    private static final String QUEUE_CAPACITY = "server.queue.capacity";

    private final Properties applicationProperties = new Properties();

    public AppSettings() {
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream propertiesStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_RESOURCE)) {
            if (propertiesStream == null) {
                throw new FileNotFoundException(
                        "Property file '" + PROPERTIES_RESOURCE + "' not found in the classpath");
            }
            applicationProperties.load(propertiesStream);
        } catch (final IOException exio) {
            setDefaultProperties();

            LOGGER.warn("Loading default properties", exio);
        }
    }

    private void setDefaultProperties() {
        applicationProperties.setProperty(REPOSITORY_FACTORY_KEY,
                "jpa.persistence.JpaRepositoryFactory");
        applicationProperties.setProperty(UI_MENU_LAYOUT_KEY, "vertical");
        applicationProperties.setProperty(PERSISTENCE_UNIT_KEY, "shodrone.app");
        applicationProperties.setProperty(SHODRONE_DOMAIN, "shodrone.app");
    }

    public boolean isMenuLayoutHorizontal() {
        return "horizontal".equalsIgnoreCase(this.applicationProperties.getProperty(UI_MENU_LAYOUT_KEY));
    }

    public String persistenceUnitName() {
        return applicationProperties.getProperty(PERSISTENCE_UNIT_KEY);
    }

    public String repositoryFactory() {
        return applicationProperties.getProperty(REPOSITORY_FACTORY_KEY);
    }

    public String shodroneDomain() {
        return applicationProperties.getProperty(SHODRONE_DOMAIN);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map extendedPersistenceProperties() {
        final Map ret = new HashMap();
        ret.put(SCHEMA_GENERATION_KEY, applicationProperties.getProperty(SCHEMA_GENERATION_KEY));
        return ret;
    }
    public String serverPort() {
        return applicationProperties.getProperty(SERVER_PORT);
    }

    public String serverIP() {
        return applicationProperties.getProperty(SERVER_IP);
    }

    public String databasePort() {
        return applicationProperties.getProperty(DATABASE_PORT);
    }

    public String getTemplatePathPortuguese() {
        return applicationProperties.getProperty(TEMPLATE_PATH_PORTUGUESE);
    }

    public String getTemplatePathEnglish() {
        return applicationProperties.getProperty(TEMPLATE_PATH_ENGLISH);
    }

    public String getTemplatePathEnglishVIP() {
        return applicationProperties.getProperty(TEMPLATE_PATH_ENGLISH_VIP);
    }

    public String getReportPath() {
        return applicationProperties.getProperty(REPORT_PATH);
    }

    public String getProperty(final String prop) {
        return applicationProperties.getProperty(prop);
    }

    public String queueCapacity() {
        return applicationProperties.getProperty(QUEUE_CAPACITY);
    }

    public boolean useEventfulControllers() {
        return Boolean.parseBoolean(applicationProperties.getProperty(USE_EVENTFUL_CONTROLLERS));
    }
}
