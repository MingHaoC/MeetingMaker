import { FC } from "react";
import { useEffect } from "react";
import { connect } from "react-redux";
import { ToastContainer } from "react-toastify";
import { StoreState } from "../reducers";
import { alertMessage } from "../actions/";
import { alert } from "../ultis/toast";
import "react-toastify/dist/ReactToastify.css";

interface Props {
  alertOption: alertMessage;
}

const Alert: FC<Props> = ({ alertOption }) => {
  useEffect(() => {
    if (alertOption.message !== "") alert(alertOption);
  }, [alertOption]);
  return (
    <div>
      <ToastContainer />
    </div>
  );
};

const mapStateToProps = (state: StoreState) => {
  return { alertOption: state.AlertReducer };
};

export default connect(mapStateToProps, {})(Alert);
