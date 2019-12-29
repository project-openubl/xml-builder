import { AxiosError, AxiosResponse } from "axios";
import { Dispatch } from "redux";
import { ThunkAction } from "redux-thunk";
import { createAction } from "typesafe-actions";
import { OrganizationRepresentation } from "../../models/xml-builder";
import { getAll } from "../../api/organizations";
import { RootState } from "../rootReducer";

export const fetchOrganizationListRequest = createAction(
  "organizationList/fetch/request"
)();
export const fetchOrganizationListSuccess = createAction(
  "organizationList/fetch/success"
)<OrganizationRepresentation[]>();
export const fetchOrganizationListFailure = createAction(
  "organizationList/fetch/failure"
)<AxiosError>();

export const fetchOrganizations = (
  filterText: string,
  page: number,
  pageSize: number
): ThunkAction<void, RootState, void, any> => {
  return (dispatch: Dispatch) => {
    dispatch(fetchOrganizationListRequest());

    return getAll(filterText, page, pageSize)
      .then((res: AxiosResponse<OrganizationRepresentation[]>) => {
        const data: OrganizationRepresentation[] = res.data;
        dispatch(fetchOrganizationListSuccess(data));
        return data;
      })
      .catch((err: AxiosError) => {
        dispatch(fetchOrganizationListFailure(err));
      });
  };
};


