export const ErrorMessage = ({ message }: { message: string }) => {
  return (
    <div className="error-message">
      <span>⚠️ Error: {message}</span>
    </div>
  );
};
