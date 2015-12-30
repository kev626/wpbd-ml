/*
 * FlyThruTowerModel.java
 *   
 * Copyright (C) 2009 Eugene K. Ressler
 *   
 * This program is distributed in the hope that it will be useful,  
 * but WITHOUT ANY WARRANTY; without even the implied warranty of  
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the  
 * GNU General Public License for more details.  
 *   
 * You should have received a copy of the GNU General Public License  
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.  
 */

package bridgedesigner;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.GL2;

/**
 * Electrical tower model for animation.
 * 
 * Generated by Sketchup plugin.
 */
public class FlyThruTowerModel {
    
    private class TriangleStrip {
        TriangleStrip(float [] c, Texture t, float [] p, float [] n, float [] tc) {
            color = c;
            texture = t;
            points = p;
            normals = n;
            texCoords = tc;
        }
        float [] color;
        Texture texture;
        float [] points;
        float [] normals;
        float [] texCoords;
    };
    private static float [] Color_0072_DarkSeaGreen = { 0.5608f, 0.7373f, 0.5608f , 1f};

    private TriangleStrip [] strips = {
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.10000f, 15.00000f,  0.17321f,
                  0.20000f, 15.00000f, -0.00000f,
                 -0.00000f, 15.20000f, -0.00000f,
            },
            new float [] { // normals
                  0.65465f,  0.65465f,  0.37796f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.08755f, 11.66695f, -0.10860f,
                 -0.03645f, 12.39932f, -2.50055f,
                  0.03655f, 12.39932f, -2.50055f,
                 -0.03645f, 12.39932f, -2.50055f,
                  0.08755f, 11.66695f, -0.10860f,
                 -0.08745f, 11.66695f, -0.10860f,
            },
            new float [] { // normals
                  0.00000f, -0.95618f, -0.29277f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.10000f, 15.00000f,  0.17321f,
                 -0.00000f, 15.20000f, -0.00000f,
                 -0.20000f, 15.00000f, -0.00000f,
            },
            new float [] { // normals
                 -0.65465f,  0.65465f,  0.37796f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.03630f, 13.89923f,  2.48432f,
                 -0.17480f, 13.31325f,  0.05314f,
                 -0.08730f, 13.16686f,  0.09236f,
                 -0.17480f, 13.31325f,  0.05314f,
                 -0.03630f, 13.89923f,  2.48432f,
                 -0.07280f, 13.96030f,  2.46795f,
            },
            new float [] { // normals
                 -0.86549f, -0.47352f,  0.16344f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.03655f, 12.39932f,  2.50055f,
                 -0.03655f, 12.52145f,  2.46783f,
                 -0.07305f, 12.46039f,  2.48419f,
                 -0.03655f, 12.52145f,  2.46783f,
                 -0.03655f, 12.39932f,  2.50055f,
                  0.03645f, 12.39932f,  2.50055f,
                 -0.03655f, 12.52145f,  2.46783f,
                  0.03645f, 12.39932f,  2.50055f,
                  0.03645f, 12.52145f,  2.46783f,
                  0.03645f, 12.52145f,  2.46783f,
                  0.03645f, 12.39932f,  2.50055f,
                  0.07295f, 12.46039f,  2.48419f,
            },
            new float [] { // normals
                  0.00000f,  0.25882f,  0.96593f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.08745f, 11.66695f,  0.10860f,
                 -0.03655f, 12.39932f,  2.50055f,
                 -0.08755f, 11.66695f,  0.10860f,
                 -0.03655f, 12.39932f,  2.50055f,
                  0.08745f, 11.66695f,  0.10860f,
                  0.03645f, 12.39932f,  2.50055f,
            },
            new float [] { // normals
                 -0.00000f, -0.95618f,  0.29277f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.08745f, 11.95973f, -0.03015f,
                 -0.07295f, 12.46039f, -2.48419f,
                 -0.17495f, 11.81334f, -0.06938f,
                 -0.07295f, 12.46039f, -2.48419f,
                 -0.08745f, 11.95973f, -0.03015f,
                 -0.03645f, 12.52145f, -2.46783f,
            },
            new float [] { // normals
                 -0.86549f,  0.49180f,  0.09522f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.00000f, 15.20000f, -0.00000f,
                 -0.10000f, 15.00000f, -0.17321f,
                 -0.20000f, 15.00000f, -0.00000f,
            },
            new float [] { // normals
                 -0.65465f,  0.65465f, -0.37796f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.03655f, 12.39932f, -2.50055f,
                  0.17505f, 11.81334f, -0.06938f,
                  0.08755f, 11.66695f, -0.10860f,
                  0.17505f, 11.81334f, -0.06938f,
                  0.03655f, 12.39932f, -2.50055f,
                  0.07305f, 12.46039f, -2.48419f,
            },
            new float [] { // normals
                  0.86549f, -0.47352f, -0.16344f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.08745f, 11.95973f, -0.03015f,
                  0.03655f, 12.52145f, -2.46783f,
                 -0.03645f, 12.52145f, -2.46783f,
                  0.03655f, 12.52145f, -2.46783f,
                 -0.08745f, 11.95973f, -0.03015f,
                  0.08755f, 11.95973f, -0.03015f,
            },
            new float [] { // normals
                  0.00000f,  0.97446f,  0.22455f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.07305f, 12.46039f, -2.48419f,
                  0.08755f, 11.95973f, -0.03015f,
                  0.17505f, 11.81334f, -0.06938f,
                  0.08755f, 11.95973f, -0.03015f,
                  0.07305f, 12.46039f, -2.48419f,
                  0.03655f, 12.52145f, -2.46783f,
            },
            new float [] { // normals
                  0.86549f,  0.49180f,  0.09522f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.10000f, 15.00000f,  0.17321f,
                 -0.00000f, 15.20000f, -0.00000f,
                 -0.10000f, 15.00000f,  0.17321f,
            },
            new float [] { // normals
                 -0.00000f,  0.65465f,  0.75593f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.03670f, 14.02136f, -2.45159f,
                 -0.03670f, 13.89923f, -2.48432f,
                 -0.07320f, 13.96030f, -2.46795f,
                 -0.03670f, 13.89923f, -2.48432f,
                 -0.03670f, 14.02136f, -2.45159f,
                  0.03630f, 14.02136f, -2.45159f,
                 -0.03670f, 13.89923f, -2.48432f,
                  0.03630f, 14.02136f, -2.45159f,
                  0.03630f, 13.89923f, -2.48432f,
                  0.03630f, 13.89923f, -2.48432f,
                  0.03630f, 14.02136f, -2.45159f,
                  0.07280f, 13.96030f, -2.46795f,
            },
            new float [] { // normals
                 -0.00000f,  0.25882f, -0.96593f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.08770f, 13.45964f,  0.01391f,
                  0.07320f, 13.96030f,  2.46795f,
                  0.17520f, 13.31325f,  0.05314f,
                  0.07320f, 13.96030f,  2.46795f,
                  0.08770f, 13.45964f,  0.01391f,
                  0.03670f, 14.02136f,  2.45159f,
            },
            new float [] { // normals
                  0.86549f,  0.49180f, -0.09522f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.03655f, 12.52145f,  2.46783f,
                  0.08745f, 11.95973f,  0.03015f,
                 -0.08755f, 11.95973f,  0.03015f,
                  0.08745f, 11.95973f,  0.03015f,
                 -0.03655f, 12.52145f,  2.46783f,
                  0.03645f, 12.52145f,  2.46783f,
            },
            new float [] { // normals
                  0.00000f,  0.97446f, -0.22455f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.07305f, 12.46039f,  2.48419f,
                 -0.08755f, 11.95973f,  0.03015f,
                 -0.17505f, 11.81334f,  0.06938f,
                 -0.08755f, 11.95973f,  0.03015f,
                 -0.07305f, 12.46039f,  2.48419f,
                 -0.03655f, 12.52145f,  2.46783f,
            },
            new float [] { // normals
                 -0.86549f,  0.49180f, -0.09522f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.03655f, 12.39932f,  2.50055f,
                 -0.17505f, 11.81334f,  0.06938f,
                 -0.08755f, 11.66695f,  0.10860f,
                 -0.17505f, 11.81334f,  0.06938f,
                 -0.03655f, 12.39932f,  2.50055f,
                 -0.07305f, 12.46039f,  2.48419f,
            },
            new float [] { // normals
                 -0.86549f, -0.47352f,  0.16344f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.17495f, 11.81334f,  0.06938f,
                  0.03645f, 12.39932f,  2.50055f,
                  0.08745f, 11.66695f,  0.10860f,
                  0.03645f, 12.39932f,  2.50055f,
                  0.17495f, 11.81334f,  0.06938f,
                  0.07295f, 12.46039f,  2.48419f,
            },
            new float [] { // normals
                  0.86549f, -0.47352f,  0.16344f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.03630f, 13.89923f, -2.48432f,
                  0.17480f, 13.31325f, -0.05314f,
                  0.08730f, 13.16686f, -0.09236f,
                  0.17480f, 13.31325f, -0.05314f,
                  0.03630f, 13.89923f, -2.48432f,
                  0.07280f, 13.96030f, -2.46795f,
            },
            new float [] { // normals
                  0.86549f, -0.47352f, -0.16344f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.03630f, 14.02136f,  2.45159f,
                  0.08770f, 13.45964f,  0.01391f,
                 -0.08730f, 13.45964f,  0.01391f,
                  0.08770f, 13.45964f,  0.01391f,
                 -0.03630f, 14.02136f,  2.45159f,
                  0.03670f, 14.02136f,  2.45159f,
            },
            new float [] { // normals
                 -0.00000f,  0.97446f, -0.22455f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.17520f, 13.31325f, -0.05314f,
                 -0.03670f, 13.89923f, -2.48432f,
                 -0.08770f, 13.16686f, -0.09236f,
                 -0.03670f, 13.89923f, -2.48432f,
                 -0.17520f, 13.31325f, -0.05314f,
                 -0.07320f, 13.96030f, -2.46795f,
            },
            new float [] { // normals
                 -0.86549f, -0.47352f, -0.16344f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.03670f, 13.89923f,  2.48432f,
                 -0.08730f, 13.16686f,  0.09236f,
                  0.08770f, 13.16686f,  0.09236f,
                 -0.08730f, 13.16686f,  0.09236f,
                  0.03670f, 13.89923f,  2.48432f,
                 -0.03630f, 13.89923f,  2.48432f,
            },
            new float [] { // normals
                 -0.00000f, -0.95618f,  0.29277f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.08770f, 13.45964f, -0.01391f,
                  0.03630f, 14.02136f, -2.45159f,
                 -0.03670f, 14.02136f, -2.45159f,
                  0.03630f, 14.02136f, -2.45159f,
                 -0.08770f, 13.45964f, -0.01391f,
                  0.08730f, 13.45964f, -0.01391f,
            },
            new float [] { // normals
                 -0.00000f,  0.97446f,  0.22455f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.03630f, 13.89923f,  2.48432f,
                 -0.03630f, 14.02136f,  2.45159f,
                 -0.07280f, 13.96030f,  2.46795f,
                 -0.03630f, 14.02136f,  2.45159f,
                 -0.03630f, 13.89923f,  2.48432f,
                  0.03670f, 13.89923f,  2.48432f,
                 -0.03630f, 14.02136f,  2.45159f,
                  0.03670f, 13.89923f,  2.48432f,
                  0.03670f, 14.02136f,  2.45159f,
                  0.03670f, 14.02136f,  2.45159f,
                  0.03670f, 13.89923f,  2.48432f,
                  0.07320f, 13.96030f,  2.46795f,
            },
            new float [] { // normals
                 -0.00000f,  0.25882f,  0.96593f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.20000f, 15.00000f, -0.00000f,
                  0.10000f, 15.00000f, -0.17321f,
                 -0.00000f, 15.20000f, -0.00000f,
            },
            new float [] { // normals
                  0.65465f,  0.65465f, -0.37796f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.03645f, 12.52145f, -2.46783f,
                 -0.03645f, 12.39932f, -2.50055f,
                 -0.07295f, 12.46039f, -2.48419f,
                 -0.03645f, 12.39932f, -2.50055f,
                 -0.03645f, 12.52145f, -2.46783f,
                  0.03655f, 12.52145f, -2.46783f,
                 -0.03645f, 12.39932f, -2.50055f,
                  0.03655f, 12.52145f, -2.46783f,
                  0.03655f, 12.39932f, -2.50055f,
                  0.03655f, 12.39932f, -2.50055f,
                  0.03655f, 12.52145f, -2.46783f,
                  0.07305f, 12.46039f, -2.48419f,
            },
            new float [] { // normals
                  0.00000f,  0.25882f, -0.96593f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.17495f, 11.81334f, -0.06938f,
                 -0.03645f, 12.39932f, -2.50055f,
                 -0.08745f, 11.66695f, -0.10860f,
                 -0.03645f, 12.39932f, -2.50055f,
                 -0.17495f, 11.81334f, -0.06938f,
                 -0.07295f, 12.46039f, -2.48419f,
            },
            new float [] { // normals
                 -0.86549f, -0.47352f, -0.16344f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.10000f, 15.00000f, -0.17321f,
                 -0.10000f, 15.00000f, -0.17321f,
                 -0.00000f, 15.20000f, -0.00000f,
            },
            new float [] { // normals
                 -0.00000f,  0.65465f, -0.75593f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.08730f, 13.16686f, -0.09236f,
                 -0.03670f, 13.89923f, -2.48432f,
                  0.03630f, 13.89923f, -2.48432f,
                 -0.03670f, 13.89923f, -2.48432f,
                  0.08730f, 13.16686f, -0.09236f,
                 -0.08770f, 13.16686f, -0.09236f,
            },
            new float [] { // normals
                 -0.00000f, -0.95618f, -0.29277f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.08770f, 13.45964f, -0.01391f,
                 -0.07320f, 13.96030f, -2.46795f,
                 -0.17520f, 13.31325f, -0.05314f,
                 -0.07320f, 13.96030f, -2.46795f,
                 -0.08770f, 13.45964f, -0.01391f,
                 -0.03670f, 14.02136f, -2.45159f,
            },
            new float [] { // normals
                 -0.86549f,  0.49180f,  0.09522f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.17520f, 13.31325f,  0.05314f,
                  0.03670f, 13.89923f,  2.48432f,
                  0.08770f, 13.16686f,  0.09236f,
                  0.03670f, 13.89923f,  2.48432f,
                  0.17520f, 13.31325f,  0.05314f,
                  0.07320f, 13.96030f,  2.46795f,
            },
            new float [] { // normals
                  0.86549f, -0.47352f,  0.16344f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.07280f, 13.96030f,  2.46795f,
                 -0.08730f, 13.45964f,  0.01391f,
                 -0.17480f, 13.31325f,  0.05314f,
                 -0.08730f, 13.45964f,  0.01391f,
                 -0.07280f, 13.96030f,  2.46795f,
                 -0.03630f, 14.02136f,  2.45159f,
            },
            new float [] { // normals
                 -0.86549f,  0.49180f, -0.09522f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.07280f, 13.96030f, -2.46795f,
                  0.08730f, 13.45964f, -0.01391f,
                  0.17480f, 13.31325f, -0.05314f,
                  0.08730f, 13.45964f, -0.01391f,
                  0.07280f, 13.96030f, -2.46795f,
                  0.03630f, 14.02136f, -2.45159f,
            },
            new float [] { // normals
                  0.86549f,  0.49180f,  0.09522f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.08745f, 11.95973f,  0.03015f,
                  0.07295f, 12.46039f,  2.48419f,
                  0.17495f, 11.81334f,  0.06938f,
                  0.07295f, 12.46039f,  2.48419f,
                  0.08745f, 11.95973f,  0.03015f,
                  0.03645f, 12.52145f,  2.46783f,
            },
            new float [] { // normals
                  0.86549f,  0.49180f, -0.09522f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.10000f, 15.00000f,  0.17321f,
                 -0.35000f,  0.00000f, -0.00000f,
                 -0.17500f,  0.00000f,  0.30311f,
                 -0.35000f,  0.00000f, -0.00000f,
                 -0.10000f, 15.00000f,  0.17321f,
                 -0.20000f, 15.00000f, -0.00000f,
            },
            new float [] { // normals
                 -0.86599f,  0.00866f,  0.49998f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.17500f,  0.00000f,  0.30311f,
                 -0.10000f, 15.00000f,  0.17321f,
                 -0.17500f,  0.00000f,  0.30311f,
                 -0.10000f, 15.00000f,  0.17321f,
                  0.17500f,  0.00000f,  0.30311f,
                  0.10000f, 15.00000f,  0.17321f,
            },
            new float [] { // normals
                 -0.00000f,  0.00866f,  0.99996f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.10000f, 15.00000f, -0.17321f,
                  0.35000f,  0.00000f, -0.00000f,
                  0.17500f,  0.00000f, -0.30311f,
                  0.35000f,  0.00000f, -0.00000f,
                  0.10000f, 15.00000f, -0.17321f,
                  0.20000f, 15.00000f, -0.00000f,
            },
            new float [] { // normals
                  0.86599f,  0.00866f, -0.49998f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.20000f, 15.00000f, -0.00000f,
                  0.17500f,  0.00000f,  0.30311f,
                  0.35000f,  0.00000f, -0.00000f,
                  0.17500f,  0.00000f,  0.30311f,
                  0.20000f, 15.00000f, -0.00000f,
                  0.10000f, 15.00000f,  0.17321f,
            },
            new float [] { // normals
                  0.86599f,  0.00866f,  0.49998f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.20000f, 15.00000f, -0.00000f,
                 -0.17500f,  0.00000f, -0.30311f,
                 -0.35000f,  0.00000f, -0.00000f,
                 -0.17500f,  0.00000f, -0.30311f,
                 -0.20000f, 15.00000f, -0.00000f,
                 -0.10000f, 15.00000f, -0.17321f,
            },
            new float [] { // normals
                 -0.86599f,  0.00866f, -0.49998f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.10000f, 15.00000f, -0.17321f,
                  0.17500f,  0.00000f, -0.30311f,
                 -0.17500f,  0.00000f, -0.30311f,
                  0.17500f,  0.00000f, -0.30311f,
                 -0.10000f, 15.00000f, -0.17321f,
                  0.10000f, 15.00000f, -0.17321f,
            },
            new float [] { // normals
                  0.00000f,  0.00866f, -0.99996f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.09050f, 10.45979f, -0.04314f,
                  0.03350f, 11.02151f, -2.48082f,
                 -0.03950f, 11.02151f, -2.48082f,
                  0.03350f, 11.02151f, -2.48082f,
                 -0.09050f, 10.45979f, -0.04314f,
                  0.08450f, 10.45979f, -0.04314f,
            },
            new float [] { // normals
                 -0.00000f,  0.97446f,  0.22455f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.03950f, 11.02151f, -2.48082f,
                 -0.03950f, 10.89938f, -2.51354f,
                 -0.07600f, 10.96044f, -2.49718f,
                 -0.03950f, 10.89938f, -2.51354f,
                 -0.03950f, 11.02151f, -2.48082f,
                  0.03350f, 11.02151f, -2.48082f,
                 -0.03950f, 10.89938f, -2.51354f,
                  0.03350f, 11.02151f, -2.48082f,
                  0.03350f, 10.89938f, -2.51354f,
                  0.03350f, 10.89938f, -2.51354f,
                  0.03350f, 11.02151f, -2.48082f,
                  0.07000f, 10.96044f, -2.49718f,
            },
            new float [] { // normals
                  0.00000f,  0.25882f, -0.96593f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.08450f, 10.16701f, -0.12159f,
                 -0.03950f, 10.89938f, -2.51354f,
                  0.03350f, 10.89938f, -2.51354f,
                 -0.03950f, 10.89938f, -2.51354f,
                  0.08450f, 10.16701f, -0.12159f,
                 -0.09050f, 10.16701f, -0.12159f,
            },
            new float [] { // normals
                  0.00000f, -0.95618f, -0.29277f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.09050f, 10.45979f, -0.04314f,
                 -0.07600f, 10.96044f, -2.49718f,
                 -0.17800f, 10.31340f, -0.08237f,
                 -0.07600f, 10.96044f, -2.49718f,
                 -0.09050f, 10.45979f, -0.04314f,
                 -0.03950f, 11.02151f, -2.48082f,
            },
            new float [] { // normals
                 -0.86549f,  0.49180f,  0.09522f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.07000f, 10.96044f, -2.49718f,
                  0.08450f, 10.45979f, -0.04314f,
                  0.17200f, 10.31340f, -0.08237f,
                  0.08450f, 10.45979f, -0.04314f,
                  0.07000f, 10.96044f, -2.49718f,
                  0.03350f, 11.02151f, -2.48082f,
            },
            new float [] { // normals
                  0.86549f,  0.49180f,  0.09522f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.03350f, 10.89938f, -2.51354f,
                  0.17200f, 10.31340f, -0.08237f,
                  0.08450f, 10.16701f, -0.12159f,
                  0.17200f, 10.31340f, -0.08237f,
                  0.03350f, 10.89938f, -2.51354f,
                  0.07000f, 10.96044f, -2.49718f,
            },
            new float [] { // normals
                  0.86549f, -0.47352f, -0.16344f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.17800f, 10.31340f, -0.08237f,
                 -0.03950f, 10.89938f, -2.51354f,
                 -0.09050f, 10.16701f, -0.12159f,
                 -0.03950f, 10.89938f, -2.51354f,
                 -0.17800f, 10.31340f, -0.08237f,
                 -0.07600f, 10.96044f, -2.49718f,
            },
            new float [] { // normals
                 -0.86549f, -0.47352f, -0.16344f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.03503f, 11.02151f,  2.48082f,
                  0.08897f, 10.45979f,  0.04314f,
                 -0.08603f, 10.45979f,  0.04314f,
                  0.08897f, 10.45979f,  0.04314f,
                 -0.03503f, 11.02151f,  2.48082f,
                  0.03797f, 11.02151f,  2.48082f,
            },
            new float [] { // normals
                  0.00000f,  0.97446f, -0.22455f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.07153f, 10.96044f,  2.49718f,
                 -0.08603f, 10.45979f,  0.04314f,
                 -0.17353f, 10.31339f,  0.08237f,
                 -0.08603f, 10.45979f,  0.04314f,
                 -0.07153f, 10.96044f,  2.49718f,
                 -0.03503f, 11.02151f,  2.48082f,
            },
            new float [] { // normals
                 -0.86549f,  0.49180f, -0.09522f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.03503f, 10.89938f,  2.51354f,
                 -0.17353f, 10.31339f,  0.08237f,
                 -0.08603f, 10.16700f,  0.12159f,
                 -0.17353f, 10.31339f,  0.08237f,
                 -0.03503f, 10.89938f,  2.51354f,
                 -0.07153f, 10.96044f,  2.49718f,
            },
            new float [] { // normals
                 -0.86549f, -0.47352f,  0.16344f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                 -0.03503f, 10.89938f,  2.51354f,
                 -0.03503f, 11.02151f,  2.48082f,
                 -0.07153f, 10.96044f,  2.49718f,
                 -0.03503f, 11.02151f,  2.48082f,
                 -0.03503f, 10.89938f,  2.51354f,
                  0.03797f, 10.89938f,  2.51354f,
                 -0.03503f, 11.02151f,  2.48082f,
                  0.03797f, 10.89938f,  2.51354f,
                  0.03797f, 11.02151f,  2.48082f,
                  0.03797f, 11.02151f,  2.48082f,
                  0.03797f, 10.89938f,  2.51354f,
                  0.07447f, 10.96044f,  2.49718f,
            },
            new float [] { // normals
                  0.00000f,  0.25882f,  0.96593f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.08897f, 10.45979f,  0.04314f,
                  0.07447f, 10.96044f,  2.49718f,
                  0.17647f, 10.31339f,  0.08237f,
                  0.07447f, 10.96044f,  2.49718f,
                  0.08897f, 10.45979f,  0.04314f,
                  0.03797f, 11.02151f,  2.48082f,
            },
            new float [] { // normals
                  0.86549f,  0.49180f, -0.09522f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.08897f, 10.16700f,  0.12159f,
                 -0.03503f, 10.89938f,  2.51354f,
                 -0.08603f, 10.16700f,  0.12159f,
                 -0.03503f, 10.89938f,  2.51354f,
                  0.08897f, 10.16700f,  0.12159f,
                  0.03797f, 10.89938f,  2.51354f,
            },
            new float [] { // normals
                 -0.00000f, -0.95618f,  0.29277f,
            },
            null),
        new TriangleStrip(Color_0072_DarkSeaGreen, null,
            new float [] { // points
                  0.17647f, 10.31339f,  0.08237f,
                  0.03797f, 10.89938f,  2.51354f,
                  0.08897f, 10.16700f,  0.12159f,
                  0.03797f, 10.89938f,  2.51354f,
                  0.17647f, 10.31339f,  0.08237f,
                  0.07447f, 10.96044f,  2.49718f,
            },
            new float [] { // normals
                  0.86549f, -0.47352f,  0.16344f,
            },
            null),
    };
    /**
     * Display the electrical tower.  Origin is at base.  Branches point in z- and negative z-direction.
     * 
     * @param gl  GL2 context for drawing
     */
    public void display(GL2 gl) {
        for (int i = 0; i < strips.length; i++) {
            TriangleStrip strip = strips[i];
            if (strip.color != null) {
                gl.glColor3fv(strip.color, 0);
            }
            gl.glBegin(GL2.GL_TRIANGLES);
            gl.glNormal3fv(strip.normals, 0);
            for (int j = 0; j < strip.points.length; j += 3) {
                gl.glVertex3fv(strip.points, j);
            }
            gl.glEnd();
        }
    }
};
