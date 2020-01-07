import React from "react";
import { Button, Modal } from "@patternfly/react-core";
import {
  ComponentRepresentation,
  ComponentTypeRepresentation
} from "../../models/xml-builder";
import { XmlBuilderRouterProps } from "../../models/routerProps";
import ProviderForm from "../../PresentationalComponents/ProviderForm";
import { ETIME } from "constants";

interface StateToProps {}

interface DispatchToProps {
  requestCreateComponent: (
    organizationId: string,
    component: ComponentRepresentation
  ) => any;
  requestUpdateComponent: (
    organizationId: string,
    component: ComponentRepresentation
  ) => any;
}

interface Props extends StateToProps, DispatchToProps, XmlBuilderRouterProps {
  component: ComponentRepresentation | undefined;
  provider: ComponentTypeRepresentation | undefined;
}

interface State {
  saving: boolean;
  formData: any | null;
}

class ManageProviderModal extends React.Component<Props, State> {
  constructor(props: Props) {
    super(props);
    this.state = {
      saving: false,
      formData: null
    };
  }

  getRedirectTo = (): string => {
    return `/organizations/manage/${this.getOrganizationId()}/keys/providers`;
  };

  getOrganizationId = (): string => {
    const { match } = this.props;
    return match.params.organizationId;
  };

  getPayload = () => {
    const { component } = this.props;
    const { formData } = this.state;

    const { id, name, ...config } = formData;
    const configPayload: any = {};
    Object.keys(config).forEach((key: string) => {
      configPayload[key] = [config[key].toString()];
    });

    return {
      ...component,
      name: name,
      config: configPayload
    };
  };

  create = () => {
    const { requestCreateComponent, provider } = this.props;
    const payload: any = {
      ...this.getPayload(),
      providerId: provider ? provider.id : undefined
    };
    requestCreateComponent(this.getOrganizationId(), payload);
  };

  update = () => {
    const { requestUpdateComponent } = this.props;
    const payload: any = {
      ...this.getPayload()
    };
    requestUpdateComponent(this.getOrganizationId(), payload);
  };

  // Handlers

  handleModalClose = () => {
    const { history } = this.props;
    history.push(this.getRedirectTo());
  };

  handleModalSave = () => {
    const { component } = this.props;
    this.setState({ saving: true }, () => {
      if (component) {
        this.update();
      } else {
        this.create();
      }
    });
  };

  handleOnFormChange = (isValid: boolean, value: any): void => {
    if (isValid) {
      this.setState({ formData: value });
    } else {
      this.setState({ formData: null });
    }
  };

  render() {
    const { component, provider } = this.props;
    const { saving, formData } = this.state;

    return (
      <React.Fragment>
        <Modal
          title={(component ? "Editar" : "Crear") + " componente"}
          isOpen={true}
          isLarge={true}
          onClose={this.handleModalClose}
          actions={[
            <Button
              key="confirm"
              variant="primary"
              onClick={this.handleModalSave}
              isDisabled={!formData || saving}
            >
              {saving ? "Guardando" : "Guardar"}
            </Button>,
            <Button key="cancel" variant="link" onClick={this.handleModalClose}>
              Cancelar
            </Button>
          ]}
        >
          <ProviderForm
            key={component ? component.id : "create-component-modal-id"}
            component={component}
            provider={provider}
            onChange={this.handleOnFormChange}
          />
        </Modal>
      </React.Fragment>
    );
  }
}

export default ManageProviderModal;
