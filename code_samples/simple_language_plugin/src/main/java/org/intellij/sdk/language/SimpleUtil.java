package org.intellij.sdk.language;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.*;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.sdk.language.psi.SimpleFile;
import org.intellij.sdk.language.psi.SimpleProperty;

import java.util.*;

public class SimpleUtil {
  
  // Searches the entire project for Simple language files with instances of the Simple property with the given key
  public static List<SimpleProperty> findProperties(Project project, String key) {
    List<SimpleProperty> result = new ArrayList<>();
    Collection<VirtualFile> virtualFiles =
          FileTypeIndex.getFiles(SimpleFileType.INSTANCE, GlobalSearchScope.allScope(project));
    for (VirtualFile virtualFile : virtualFiles) {
      SimpleFile simpleFile = (SimpleFile) PsiManager.getInstance(project).findFile(virtualFile);
      if (simpleFile != null) {
        SimpleProperty[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, SimpleProperty.class);
        if (properties != null) {
          for (SimpleProperty property : properties) {
            if (key.equals(property.getKey())) {
               result.add(property);
            }
          }
        }
      }
    }
    return result;
  }
  
  public static List<SimpleProperty> findProperties(Project project) {
    List<SimpleProperty> result = new ArrayList<SimpleProperty>();
    Collection<VirtualFile> virtualFiles =
          FileTypeIndex.getFiles(SimpleFileType.INSTANCE, GlobalSearchScope.allScope(project));
    for (VirtualFile virtualFile : virtualFiles) {
      SimpleFile simpleFile = (SimpleFile) PsiManager.getInstance(project).findFile(virtualFile);
      if (simpleFile != null) {
        SimpleProperty[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, SimpleProperty.class);
        if (properties != null) {
          Collections.addAll(result, properties);
        }
      }
    }
    return result;
  }
}