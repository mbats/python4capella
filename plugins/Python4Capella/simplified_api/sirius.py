include('workspace://Python4Capella/java_api/EMF_API.py')
if False:
    from java_api.EMF_API import *
include('workspace://Python4Capella/java_api/Sirius_API.py')
if False:
    from java_api.Sirius_API import *

class DRepresentationDescriptor(EObject):
    def __init__(self, java_object = None):
        if java_object is None:
            EObject.__init__(self, create_e_object("http://www.eclipse.org/sirius/1.1.0", "DRepresentationDescriptor"))
        elif isinstance(java_object, DRepresentationDescriptor):
            EObject.__init__(self, java_object.get_java_object())
        else:
            EObject.__init__(self, java_object)
    def get_name(self):
        return self.get_java_object().getName()
    def set_name(self, value):
        self.get_java_object().setName(value)
    def get_target(self):
        return EObject(self.get_java_object().getTarget())
    def set_target(self, value):
        return self.get_java_object().setTarget(value.get_java_object())
    def export_image(self, file_path):
        export_image_sirius(self, file_path)

def get_representation_descriptors(self):
    res = []
    for e in get_representation_descriptors_sirius(self):
        res.append(DRepresentationDescriptor(e))
    return res
setattr(EObject, "get_representation_descriptors", get_representation_descriptors)

def load_sirius_session(aird_path):
    return loadSiriusSession(aird_path)
