package org.jtheque.filmstobuy;

/*
 * This file is part of JTheque.
 *
 * JTheque is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * JTheque is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JTheque.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.feature.Feature;
import org.jtheque.core.managers.feature.Feature.FeatureType;
import org.jtheque.core.managers.feature.IFeatureManager;
import org.jtheque.core.managers.feature.IFeatureManager.CoreFeature;
import org.jtheque.core.managers.file.IFileManager;
import org.jtheque.core.managers.file.able.BackupReader;
import org.jtheque.core.managers.file.able.BackupWriter;
import org.jtheque.core.managers.file.able.FileType;
import org.jtheque.core.managers.module.annotations.Module;
import org.jtheque.core.managers.module.annotations.Plug;
import org.jtheque.core.managers.module.annotations.PrePlug;
import org.jtheque.core.managers.module.annotations.UnPlug;
import org.jtheque.core.managers.schema.ISchemaManager;
import org.jtheque.core.managers.schema.Schema;
import org.jtheque.filmstobuy.persistence.FilmsToBuySchema;

/**
 * A module to manage a list of films to buy.
 *
 * @author Baptiste Wicht
 */
@Module(id = "jtheque-filmstobuy-module", i18n = "classpath:org/jtheque/filmstobuy/i18n/filmstobuy",
        version = "1.3.1-SNAPSHOT", core = "2.0.2", jarFile = "jtheque-filmstobuy-module-1.3.1-SNAPSHOT.jar",
        dependencies = {"jtheque-films-module"}, updateURL = "http://jtheque.developpez.com/public/versions/FilmsToBuyModule.versions")
public final class FilmsToBuyModule {
    private Feature toBuyFeature;

    private final BackupWriter xmlWriter;
    private final BackupWriter jtdWriter;
    private final BackupReader xmlReader;
    private final BackupReader jtdReader;
    private final BackupReader dbV4Reader;

    private Schema schema;

    /**
     * Construct a new FilmsToBuyModule.
     *
     * @param xmlWriter  A XML backup writer.
     * @param jtdWriter  A JTD backup writer.
     * @param xmlReader  A XML backup reader.
     * @param jtdReader  A JTD backup reader.
     * @param dbV4Reader A DB backup reader.
     */
    public FilmsToBuyModule(BackupWriter xmlWriter, BackupWriter jtdWriter, BackupReader xmlReader, BackupReader jtdReader,
                            BackupReader dbV4Reader) {
        super();

        this.xmlWriter = xmlWriter;
        this.jtdWriter = jtdWriter;
        this.xmlReader = xmlReader;
        this.jtdReader = jtdReader;
        this.dbV4Reader = dbV4Reader;
    }

    /**
     * Pre plug the module.
     */
    @PrePlug
    public void prePlug() {
        schema = new FilmsToBuySchema();

        Managers.getManager(ISchemaManager.class).registerSchema(schema);
    }

    /**
     * Plug the module.
     */
    @Plug
    public void plug() {
        toBuyFeature = Managers.getManager(IFeatureManager.class).addSubFeature(
                Managers.getManager(IFeatureManager.class).getFeature(CoreFeature.ADVANCED),
                "displayFilmsToBuyViewAction", FeatureType.ACTION, 1
        );

        Managers.getManager(IFileManager.class).registerBackupWriter(FileType.XML, xmlWriter);
        Managers.getManager(IFileManager.class).registerBackupWriter(FileType.JTD, jtdWriter);

        Managers.getManager(IFileManager.class).registerBackupReader(FileType.XML, xmlReader);
        Managers.getManager(IFileManager.class).registerBackupReader(FileType.JTD, jtdReader);
        Managers.getManager(IFileManager.class).registerBackupReader(FileType.V4, dbV4Reader);
    }

    /**
     * Un plug the module.
     */
    @UnPlug
    public void unplug() {
        Managers.getManager(IFeatureManager.class).getFeature(CoreFeature.ADVANCED).removeSubFeature(toBuyFeature);

        Managers.getManager(IFileManager.class).unregisterBackupWriter(FileType.XML, xmlWriter);
        Managers.getManager(IFileManager.class).unregisterBackupWriter(FileType.JTD, jtdWriter);

        Managers.getManager(IFileManager.class).unregisterBackupReader(FileType.XML, xmlReader);
        Managers.getManager(IFileManager.class).unregisterBackupReader(FileType.JTD, jtdReader);
        Managers.getManager(IFileManager.class).unregisterBackupReader(FileType.V4, dbV4Reader);

        Managers.getManager(ISchemaManager.class).unregisterSchema(schema);
    }
}