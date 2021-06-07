/**
 *   Copyright (c) 2021 THALES GLOBAL SERVICES
 *  This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *
 *  SPDX-License-Identifier: EPL-2.0
 *
 *  Contributors:
 *       Obeo - Initial API and implementation
 */
package org.eclipse.python4capella.modules;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.ease.modules.WrapToScript;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * EASE module for EMF.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 *
 */
public class EMFModule {

	/**
	 * Gets the value of the given {@link EStructuralFeature#getName() feature name}
	 * for the given {@link EObject}.
	 * 
	 * @param eObj        the {@link EObject}
	 * @param featureName the {@link EStructuralFeature#getName() feature name}
	 * @return the value of the given {@link EStructuralFeature#getName() feature
	 *         name} for the given {@link EObject}
	 */
	@WrapToScript
	public Object eGet(EObject eObj, String featureName) {
		return eObj.eGet(eObj.eClass().getEStructuralFeature(featureName));
	}

	/**
	 * Sets the new value for the given {@link EStructuralFeature#getName() feature
	 * name} for the given {@link EObject}.
	 * 
	 * @param eObj        the {@link EObject}
	 * @param featureName the {@link EStructuralFeature#getName() feature name}
	 * @param value       the new value
	 */
	@WrapToScript
	public void eSet(EObject eObj, String featureName, Object value) {
		eObj.eSet(eObj.eClass().getEStructuralFeature(featureName), value);
	}

	/**
	 * Deletes the given {@link EObject}.
	 * 
	 * @param eObj the {@link EObject} to delete
	 */
	@WrapToScript
	public void delete(EObject eObj) {
		EcoreUtil.delete(eObj);
	}

	/**
	 * Creates an instance of the {@link EClass} in the {@link EPackage} with the
	 * given {@link EPackage#getNsURI() nsURI} and the given {@link EClass#getName()
	 * eclass name}.
	 * 
	 * @param nsURI      the {@link EPackage#getNsURI() nsURI}
	 * @param eClassName the {@link EClass#getName() eclass name}
	 * @return the created instance of the {@link EClass} in the {@link EPackage}
	 *         with the given {@link EPackage#getNsURI() nsURI} and the given
	 *         {@link EClass#getName() name}
	 */
	@WrapToScript
	public EObject create(String nsURI, String eClassName) {
		final EClassifier eClassifier = EPackage.Registry.INSTANCE.getEPackage(nsURI).getEClassifier(eClassName);
		if (eClassifier instanceof EClass) {
			return EcoreUtil.create((EClass) eClassifier);
		} else {
			throw new IllegalStateException("only instance of EClasses can be created.");
		}
	}

	/**
	 * Get the {@link EClassifier} in the {@link EPackage} with the given
	 * {@link EPackage#getNsURI() nsURI} and the given {@link EClass#getName()
	 * eclass name}.
	 * 
	 * @param nsURI      the {@link EPackage#getNsURI() nsURI}
	 * @param eClassName the {@link EClass#getName() eclass name}
	 * @return the {@link EClassifier} in the {@link EPackage} with the given
	 *         {@link EPackage#getNsURI() nsURI} and the given
	 *         {@link EClass#getName() eclass name}
	 */
	@WrapToScript
	public EClassifier getEClassifier(String nsURI, String eClassName) {
		return EPackage.Registry.INSTANCE.getEPackage(nsURI).getEClassifier(eClassName);
	}

	@WrapToScript
	public List<EObject> eAllContents(EObject eObj, EClassifier eClassifier) {
		final List<EObject> res = new ArrayList<EObject>();

		if (eClassifier instanceof EClass) {
			final EClass eCls = (EClass) eClassifier;
			final Iterator<EObject> it = eObj.eAllContents();
			while (it.hasNext()) {
				final EObject child = it.next();
				if (eCls.isInstance(child)) {
					res.add(child);
				}
			}
		}

		return res;
	}

}
